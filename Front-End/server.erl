-module(server).
-export([start/1, parse_accounts/1]).

-define(SHOST, "localhost").
-define(SPORT, 13000).

%% TODO 
%% Processo de eleicao de negociador ao qual ligar,
%% para ja e estatico, futuramente aleatorio

%%
%% funcao que arrana o servidor de front-end
%% 
%% Port - porta a usar
%%
start(Port) ->
  {ok, Listen} = gen_tcp:listen(Port, [binary, {packet, line}]),
  PID = spawn(fun() -> login_manager(#{}) end),
  aceptor(Listen, PID).

%%
%% Loop basico para aceitar ligacoes
%% 
%% Listen - ListenSocket para aceitar ligacoes
%% LM - PID do processo com o registo de Logins
%%
aceptor(Listen, LM) ->
  {ok, Sock} = gen_tcp:accept(Listen),
  io:format("[Front-end] Client connected~n", []),
  PID = spawn(fun() -> client_non_autenticated(Sock, LM) end),
  gen_tcp:controlling_process(Sock, PID),
  aceptor(Listen, LM).

%%
%% TODO ler contas de um ficheiro
%%
parse_accounts(F) ->
  true.

%%
%% ator especial que valida os logins, comunica via mensagens
%% Negociadores e clientes nao sao diferenciados, sim-nao?
%%
%% M - um map que armazena as credenciais
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
%% CS - o socket do qual se le informacao vinda do cliente
%% LM - PID do Login Manager
%%
client_non_autenticated(CS, LM) ->
  case read(CS) of 
    closed ->
      io:format("[Front-end] Client closed connection.~n", []),
      closed;
    error -> 
      io:format("[Front-end] Client closed via error.~n", []),
      error;
    Msg ->
      [Mode, Login, PW] = string:split(string:trim(
                                         binary:bin_to_list(Msg)), " ", all),
      atempt(CS, Mode, Login, PW, LM)
  end.


%%
%% funcao utilitaria para se o pretendido e criar um conta
%% ou autenticar-se
%%
%% Mode - represntacao textual do modo
%%
parse_mode(Mode) ->
  case lists:nth(1, Mode) of
    $c ->
      create;
    $l ->
      login
  end.

%%
%% tentativa de login ou cricacao de conta
%%
%% CS - Client Socket
%% Mode - mode de cricao, neste ponto sera create ou login
%% Login - login do utlizador
%% PW - password do utiizador
%% LM - PID do Login Manager
%%
atempt(CS, Mode, Login, PW, LM) ->
  io:format("[Front-End]Mode: ~p Login: ~p PW: ~p~n", [Mode, Login, PW]),
  _M = parse_mode(Mode),
  LM ! {_M, Login, PW, self()},
  case _M of 
    login ->
      handle_login(CS, LM);
    create ->
      handle_create(CS, LM)
  end.

%%
%% funcao que lida com as mensagens de resposta do LM
%% quando se tentar autenticar um utlizador
%%
%% CS - Client Sockets
%% LM - PID do login Manager
%%
handle_login(CS, LM) ->
  receive
    {user_not_exist, LM} ->
      gen_tcp:send(CS, "User already Exists\r\n"),
      client_non_autenticated(CS, LM);
    {wrong_wp, LM} ->
      gen_tcp:send(CS, "Wrong Password\r\n"),
      client_non_autenticated(CS, LM);
    {logged_in, LM} ->
      %%SS = connect_to_back_end(),
      gen_tcp:send(CS, "Welcome.\r\n"),
      client_autenticated(CS, dummy)
  end.

%%
%% Semelhante a handle_login mas para o caso de 
%% criacao de conta
%%
handle_create(CS, LM) ->
  receive
    {user_exists, LM} ->
      gen_tcp:send(CS, "Cannot create, user exists\r\n"),
      client_non_autenticated(CS, LM);
    {user_created, LM} ->
      gen_tcp:send(CS, "Welcome.\r\n"),
      %%SS = connect_to_back_end(),
      client_autenticated(CS, dummy)
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

connect_to_back_end(Host, Port) ->
  {ok, SS} = gen_tcp:connect(Host, Port),
  SS.

%%
%% funcao utilitaria para ler de um socket
%% 
read(Socket) ->
  receive
    {tcp, Socket, Data} ->
      Data;
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




