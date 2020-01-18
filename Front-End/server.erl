-module(server).
-export([start/1, parse_accounts/1]).

-include("authentication.hrl").
-include("server.hrl").
-include("autresponse.hrl").

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
  {ok, Listen} = gen_tcp:listen(Port, [binary, {active, true}]),
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
      io:format("[Client] Client closed connection.~n", []),
      closed;
    error -> 
      io:format("[Client] Client closed via error.~n", []),
      error;
    Msg ->
      AutReq = authentication:decode_msg(Msg, 'AuthenticationRequest'),
      #'AuthenticationRequest'{authType=Mode, clientType=CType,
                              username=Login, password=PW} = AutReq,
      atempt(CS, Mode, CType, Login, PW, LM)
  end.


%%
%% funcao utilitaria para se o pretendido e criar um conta
%% ou autenticar-se
%%
%% Mode - represntacao textual do modo
%%
parse_mode(Mode) ->
  case Mode of
    'REGISTER' ->
      create;
    'LOGIN' ->
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
atempt(CS, Mode, CType, Login, PW, LM) ->
  io:format("[Client]Mode: ~p Type:~p Login: ~p PW: ~p~n", [Mode, CType, Login, PW]),
  _M = parse_mode(Mode),
  case _M of 
    login ->
      handle_login(CS, CType, Login, PW, LM);
    create ->
      handle_create(CS, CType, Login, PW, LM)
  end.

%%
%% funcao que lida com as mensagens de resposta do LM
%% quando se tentar autenticar um utlizador
%%
%% CS - Client Sockets
%% LM - PID do login Manager
%%
handle_login(CS, CType, Login, PW, LM) ->
  LM ! {login, Login, PW, self()},
  receive
    {user_not_exist, LM} ->
      Msg = autresponse:encode_msg(#'AutResponse'{autResType='USER_NOT_EXISTS'}),
      gen_tcp:send(CS, msg_creation(Msg)),
      client_non_autenticated(CS, LM);
    {wrong_wp, LM} ->
      Msg = autresponse:encode_msg(#'AutResponse'{autResType='WRONG_PW'}),
      gen_tcp:send(CS, msg_creation(Msg)),
      client_non_autenticated(CS, LM);
    {logged_in, LM} ->
      Msg = autresponse:encode_msg(#'AutResponse'{autResType='LOGGED_IN'}),
      gen_tcp:send(CS, msg_creation(Msg)),
      %%SS = connect_to_back_end(),
      client_autenticated(CS, dummy, LM)
  end.

%%
%% Semelhante a handle_login mas para o caso de 
%% criacao de conta
%%
handle_create(CS, CType, Login, PW, LM) ->
  LM ! {create, Login, PW, self()},
  receive
    {user_exists, LM} ->
      Msg = autresponse:encode_msg(#'AutResponse'{autResType='USER_EXISTS'}),
      gen_tcp:send(CS, msg_creation(Msg)),
      client_non_autenticated(CS, LM);
    {user_created, LM} ->
      Msg = autresponse:encode_msg(#'AutResponse'{autResType='USER_CREATED'}),
      gen_tcp:send(CS, msg_creation(Msg)),
      %%SS = connect_to_back_end(Type),
      client_autenticated(CS, dummy, LM)
  end.

%%
%% funcao utilitaria para apender a mensagem o necessario 
%% para enviar ao  cliente
%%
%% Mgs - a mensagem a ser enviada
%%
msg_creation(Msg) ->
  Len = erlang:byte_size(Msg),
  LenBin = <<Len:32/little>>,
  [LenBin | Msg].

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

connect_to_back_end(Type) ->
  %%{ok, SS} = gen_tcp:connecT(?HOST, ?PORT, ),
  %%SS
  true.



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
%% Mantida para ja, para testes.
%%
client_autenticated(CS, SS, LM) ->
  client_loop(CS, SS, LM).


%%
%% loop basico de um client ligado
%% recebe pedidos que sao enviados 
%% ao back end server
%%
%% CS - Client Socket
%% SS - Server Socket
%% LM - Login Manager
%% (LM mantido para ja caso queira manter opcao
%% de diconnect)
%%
client_loop(CS, SS, LM) ->
  case read(CS) of
    closed -> 
      io:format("[Client] Closed~n", [ ]),
      closed;
    error ->
      io:format("[Client] Error~n", [ ]),
      error;
    Request ->
      io:format("[Client] Request Sent~n", [ ]),
      gen_tcp:send(SS, Request),
      case read(SS) of
        closed ->
          io:format("[Client] Closed~n", [ ]),
          closed;
        error ->
          io:format("[Client] Error~n", [ ]),
          error;
        Reply ->
          io:format("[Client] Reply Received~n", [ ]),
          gen_tcp:send(CS, Reply),
          client_loop(CS, SS, LM)
      end
  end.



