-module(server).
-export([start/1]).

-define(INITUSERS, #{"Cesar" => "Silva", "Pedro" => "Moura", 
"Miguel" => "Oliveira", "John" => "Doe", "Teste" => "1234"}).
-define(POA, #{'TECNOLOGIA' => 8000, 'ALIMENTACAO' => 8005, 'TEXTEIS' => 8010,
               'DIVERSOS' => 8015}).

-include("message.hrl").
-include("autresponse.hrl").

%%
%% funcao que arrana o servidor de front-end
%% 
%% Port - porta a usar
%%
start(Port) ->
  {ok, Listen} = gen_tcp:listen(Port, [binary, {active, true}]),
  M = ?INITUSERS,
  PID = spawn(fun() -> login_manager(M) end),
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
      AutReq = message:decode_msg(Msg, 'GenericMessage'),
      #'GenericMessage'{type='AUTH_REQUEST', payload={auth_request, _Aux}}= AutReq,
      #'AuthenticationRequest'{authType=Mode, area=Area, clientType=_, username=Login, password=PW} = _Aux,
      atempt(CS, Mode, Area, Login, PW, LM, Msg)
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
atempt(CS, Mode, Area, Login, PW, LM, Msg) ->
  _M = parse_mode(Mode),
  case _M of 
    login ->
      handle_login(CS, Area, Login, PW, LM, Msg);
    create ->
      handle_create(CS, Area, Login, PW, LM, Msg)
  end.

%%
%% funcao que lida com as mensagens de resposta do LM
%% quando se tentar autenticar um utlizador
%%
%% CS - Client Sockets
%% LM - PID do login Manager
%%
handle_login(CS, Area, Login, PW, LM, Msg) ->
  LM ! {login, Login, PW, self()},
  receive
    {user_not_exist, LM} ->
      gen_tcp:send(CS, msg_creation('USER_NOT_EXISTS')),
      client_non_autenticated(CS, LM);
    {wrong_wp, LM} ->
      gen_tcp:send(CS, msg_creation('WRONG_PW')),
      client_non_autenticated(CS, LM);
    {logged_in, LM} ->
      gen_tcp:send(CS, msg_creation('LOGGED_IN')),
      SS = connect_to_back_end(Area),
      client_autenticated(CS, SS, Msg)
  end.

%%
%% Semelhante a handle_login mas para o caso de 
%% criacao de conta
%%
handle_create(CS, Area, Login, PW, LM, Msg) ->
  LM ! {create, Login, PW, self()},
  receive
    {user_exists, LM} ->
      gen_tcp:send(CS, msg_creation('USER_EXISTS')),
      client_non_autenticated(CS, LM);
    {user_created, LM} ->
      gen_tcp:send(CS, msg_creation('USER_CREATED')),
      SS = connect_to_back_end(Area),
      client_autenticated(CS, SS, Msg)
  end.

%%
%% funcao utilitaria para apender a mensagem o necessario 
%% para enviar ao  cliente
%%
%% Msg - a mensagem a ser enviada
%%
msg_creation(Type) ->
  Msg = autresponse:encode_msg(#'AutResponse'{autResType=Type}),
  add_length(Msg).


add_length(Msg) ->
  Len = erlang:byte_size(Msg),
  LenBin = <<Len:32/little>>,
  [LenBin | Msg].

%%
%% funcao para ligar um ator aos servidores de back end
%%
%% TODO processo de eleicao da porta,
%% usar lista?
%%
connect_to_back_end(Type) ->
  Port = maps:get(Type, ?POA),
  {ok, SS} = gen_tcp:connect("localhost", Port, [binary, {active, true}]),
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
%% Re-envia os dados de autenticação
%% para o negociador associado para uma
%% atualização do catálogo
%%
%% CS - client socket
%% SS  - server socket(negociador)
%% Msg - mensagem com as informacoes do cliente
%%
%%
client_autenticated(CS, SS, Msg) ->
  gen_tcp:send(SS, add_length(Msg)),
  client_loop(CS, SS).


%%
%% loop basico de um client ligado
%% recebe pedidos que sao enviados 
%% ao back end server
%%
%% CS - Client Socket
%% SS - Server Socket
%%
client_loop(CS, SS) ->
  receive
    {tcp, CS, Data} ->
      io:format("[Client] Request Sent~n", [ ]),
      gen_tcp:send(SS, add_length(Data)),
      client_loop(CS, SS);
    {tcp_closed, CS} ->
      io:format("[Client] Client side close~n", [ ]),
      closed;
    {tcp_error, CS, _} ->
      io:format("[Client] Client side error~n", [ ]),
      error;
    {tcp, SS, Data} ->
      io:format("[Client] Reply Received~n", [ ]),
      gen_tcp:send(CS, add_length(Data)),
      client_loop(CS, SS);
    {tcp_closed,SS} ->
      io:format("[Client] Client side closed~n", [ ]),
      closed;
    {tcp_error, SS, _} ->
      io:format("[Client] Server side error~n", [ ]),
      error
  end.
  

