-module(server).
-export([start/1, parse_accounts/1]).

-define(SHOST, "localhost").
-define(SPORT, 12345).

%% no geral CS significa Client Socket e SS Server Socket
%% TODO 
%% Processo de eleicao de negociador ao qual ligar,
%% para ja e estatico, futuramente aleatorio

%%
%% funcao que começa o servidor
%% cria um processo especial para registar a informacao dos logins
%%
start(Port) ->
  {ok, Listen} = gen_tcp:listen(Port, [binary, {packet, line}]),
  PID = spawn(fun() -> login_manager({}) end),
  register(?MODULE, PID),
  aceptor(Listen).

%%
%% loop basico para aceitar ligacoes
%% uma ator por ligacao
%%
aceptor(Listen) ->
  {ok, Sock} = gen_tcp:accept(Listen),
  io:format("[Front-end] Client connected~n", []),
  PID = spawn(fun() -> client_non_autenticated(Sock) end),
  gen_tcp:controlling_process(Sock, PID),
  aceptor(Listen).

%%
%% TODO ler contas de um ficheiro
%%
parse_accounts(F) ->
  true.

%%
%% ator especial para validar os logins
%%
login_manager(M) ->
  receive
    {login, Login, PW, From} ->
      case maps:is_key(Login, M) of
        true ->
          _PW = maps:get(Login, M),
          case PW == _PW of
            true ->
              io:format("[Front-end] Sucessfull log-in.~n", []),
              From ! {logged_in, self()},
              login_manager(M);
            false ->
              io:format("[Front-end] Unsucessfull log-in, wrong pw~n", []),
              From ! {wrong_pw, self()},
              login_manager(M)
          end;
        false ->
          io:format("[Front-end] Unsucessfull log-in, user doesn't exist~n", []),
          From ! {user_not_exist, self()},
          login_manager(M)
      end;
    {create, Login, PW, From} ->
      case maps:is_key(Login, M) of 
        true ->
          io:format("[Front-end] Unsucessfull creation, user already exists~n", []),
          From ! {user_exists, self()},
          login_manager(M);
        false ->
          io:format("[Front-end] Sucessfull creation~n", []),
          From ! {user_created, self()},
          login_manager(maps:put(Login, PW, M))
      end
  end.

%%
%% funcao que codifica um ator por autenticar
%%
client_non_autenticated(CS) ->
  case read(CS) of
    closed ->
      io:format("[Front-end] Client closed connection.~n", []),
      closed;
    error ->
      io:format("[Front-end] Client closed connection via error.~n", []),
      error;
    Login ->
      io:format("~p~n", [Login]),
      case read(CS) of
        error ->
          true;
        closed ->
          true;
        PW ->
          login_try(CS, Login, PW)
      end
  end.

%%
%% funcao para ligar um ator aos servidores de back end
%%
%% TODO processo de eleicao da porta,
%% usar lista?
%%
connect_to_back_end() ->
  {ok, SS} = gen_tcp:connect(?SHOST, ?SPORT),
  SS.

%%
%% funcao utilitaria para tentar validar um login
%%
%%
login_try(CS, Login, PW) ->
  ?MODULE ! {login, Login, PW, self()},
  receive
    {user_not_exist, ?MODULE} ->
      client_non_autenticated(CS);
    {wrong_wp, ?MODULE} ->
      client_non_autenticated(CS);
    {logged_in, ?MODULE} ->
      SS = connect_to_back_end(),
      gen_tcp:send(CS, "Welcome.\r\n"),
      client_autenticated(CS, SS)
  end.

%%
%% funcao para validar a criacao de uma conta
%%
create_try(CS, Login, PW) ->
  ?MODULE ! {create, Login, PW, self()},
  receive
    {user_exists, ?MODULE} ->
      gen_tcp:send(CS, "Cannot create, user exists\r\n"),
      client_non_autenticated(CS);
    {user_created, ?MODULE} ->
      gen_tcp:send(CS, "Welcome.\r\n"),
      SS = connect_to_back_end(),
      client_autenticated(CS, SS)
  end.

%%
%% funcao utilitaria para ler de um socket
%% 
read(Socket) ->
  receive
    {tcp, Socket, Data} ->
      binary:bin_to_list(Data);
    {tcp_closed, Socket} ->
      closed;
    {tcp_error, Socket, _} ->
      error 
  end.

%%
%% funcao que codifica o comportamente de um client ligado
%%
%% ideia basica, depois de validado o ator simplesmente 
%% receve pedidos do client e envia para o back end para 
%% ser processado, com isto, precisa de 2 sockets.
%% Um para o client que faz pedidos e um para a ligacao ao 
%% backend para enviar informacao. 
%%
client_autenticated(CS, SS) ->
  gen_tcp:send(CS, "You are autenticated\r\n"),
  true.




