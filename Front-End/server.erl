-module(server).
-export([start/1, parse_accounts/1]).

start(Port) ->
  {ok, Listen} = gen_tcp:listen(Port, [binary, {packet, 4}]),
  PID = spawn(fun() -> login_manager({}) end),
  register(?MODULE, PID),
  aceptor(Listen).

aceptor(Listen) ->
  {ok, Sock} = gen_tcp:accept(Listen),
  PID = spawn(fun() -> client_non_autenticated(Sock) end),
  gen_tcp:controlling_process(Sock, PID),
  aceptor(Listen).

parse_accounts(F) ->
  true.

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

client_non_autenticated(CSocket) ->
  receive
    {tcp, _, Data} ->
      io:format("Got smth ~p~n", [Data])
  end,
  gen_tcp:send(CSocket, "This is only a test\r\n"),
  client_non_autenticated(CSocket).

client_autenticated(CSocket) ->
  true.




