-module(exemplo).
-export([foo/0]).
-include("teste.hrl").

foo() ->
  A = #'Person'{name="aa", id=123, email="a@e.com"},
  teste:encode_msg(A).
