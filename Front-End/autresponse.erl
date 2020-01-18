%% -*- coding: utf-8 -*-
%% @private
%% Automatically generated, do not edit
%% Generated by gpb_compile version 4.11.0
-module(autresponse).

-export([encode_msg/1, encode_msg/2, encode_msg/3]).
-export([decode_msg/2, decode_msg/3]).
-export([merge_msgs/2, merge_msgs/3, merge_msgs/4]).
-export([verify_msg/1, verify_msg/2, verify_msg/3]).
-export([get_msg_defs/0]).
-export([get_msg_names/0]).
-export([get_group_names/0]).
-export([get_msg_or_group_names/0]).
-export([get_enum_names/0]).
-export([find_msg_def/1, fetch_msg_def/1]).
-export([find_enum_def/1, fetch_enum_def/1]).
-export([enum_symbol_by_value/2, enum_value_by_symbol/2]).
-export([enum_symbol_by_value_AutResponseType/1, enum_value_by_symbol_AutResponseType/1]).
-export([get_service_names/0]).
-export([get_service_def/1]).
-export([get_rpc_names/1]).
-export([find_rpc_def/2, fetch_rpc_def/2]).
-export([fqbin_to_service_name/1]).
-export([service_name_to_fqbin/1]).
-export([fqbins_to_service_and_rpc_name/2]).
-export([service_and_rpc_name_to_fqbins/2]).
-export([fqbin_to_msg_name/1]).
-export([msg_name_to_fqbin/1]).
-export([fqbin_to_enum_name/1]).
-export([enum_name_to_fqbin/1]).
-export([get_package_name/0]).
-export([uses_packages/0]).
-export([source_basename/0]).
-export([get_all_source_basenames/0]).
-export([get_all_proto_names/0]).
-export([get_msg_containment/1]).
-export([get_pkg_containment/1]).
-export([get_service_containment/1]).
-export([get_rpc_containment/1]).
-export([get_enum_containment/1]).
-export([get_proto_by_msg_name_as_fqbin/1]).
-export([get_proto_by_service_name_as_fqbin/1]).
-export([get_proto_by_enum_name_as_fqbin/1]).
-export([get_protos_by_pkg_name_as_fqbin/1]).
-export([gpb_version_as_string/0, gpb_version_as_list/0]).

-include("autresponse.hrl").
-include("gpb.hrl").

%% enumerated types
-type 'AutResponseType'() :: 'USER_NOT_EXISTS' | 'WRONG_PW' | 'LOGGED_IN' | 'USER_EXISTS' | 'USER_CREATED'.
-export_type(['AutResponseType'/0]).

%% message types
-type 'AutResponse'() :: #'AutResponse'{}.

-export_type(['AutResponse'/0]).

-spec encode_msg(#'AutResponse'{}) -> binary().
encode_msg(Msg) when tuple_size(Msg) >= 1 ->
    encode_msg(Msg, element(1, Msg), []).

-spec encode_msg(#'AutResponse'{}, atom() | list()) -> binary().
encode_msg(Msg, MsgName) when is_atom(MsgName) ->
    encode_msg(Msg, MsgName, []);
encode_msg(Msg, Opts)
    when tuple_size(Msg) >= 1, is_list(Opts) ->
    encode_msg(Msg, element(1, Msg), Opts).

-spec encode_msg(#'AutResponse'{}, atom(), list()) -> binary().
encode_msg(Msg, MsgName, Opts) ->
    case proplists:get_bool(verify, Opts) of
      true -> verify_msg(Msg, MsgName, Opts);
      false -> ok
    end,
    TrUserData = proplists:get_value(user_data, Opts),
    case MsgName of
      'AutResponse' ->
	  encode_msg_AutResponse(id(Msg, TrUserData), TrUserData)
    end.


encode_msg_AutResponse(Msg, TrUserData) ->
    encode_msg_AutResponse(Msg, <<>>, TrUserData).


encode_msg_AutResponse(#'AutResponse'{autResType = F1},
		       Bin, TrUserData) ->
    begin
      TrF1 = id(F1, TrUserData),
      e_enum_AutResponseType(TrF1, <<Bin/binary, 8>>,
			     TrUserData)
    end.

e_enum_AutResponseType('USER_NOT_EXISTS', Bin,
		       _TrUserData) ->
    <<Bin/binary, 1>>;
e_enum_AutResponseType('WRONG_PW', Bin, _TrUserData) ->
    <<Bin/binary, 2>>;
e_enum_AutResponseType('LOGGED_IN', Bin, _TrUserData) ->
    <<Bin/binary, 3>>;
e_enum_AutResponseType('USER_EXISTS', Bin,
		       _TrUserData) ->
    <<Bin/binary, 4>>;
e_enum_AutResponseType('USER_CREATED', Bin,
		       _TrUserData) ->
    <<Bin/binary, 5>>;
e_enum_AutResponseType(V, Bin, _TrUserData) ->
    e_varint(V, Bin).

-compile({nowarn_unused_function,e_type_sint/3}).
e_type_sint(Value, Bin, _TrUserData) when Value >= 0 ->
    e_varint(Value * 2, Bin);
e_type_sint(Value, Bin, _TrUserData) ->
    e_varint(Value * -2 - 1, Bin).

-compile({nowarn_unused_function,e_type_int32/3}).
e_type_int32(Value, Bin, _TrUserData)
    when 0 =< Value, Value =< 127 ->
    <<Bin/binary, Value>>;
e_type_int32(Value, Bin, _TrUserData) ->
    <<N:64/unsigned-native>> = <<Value:64/signed-native>>,
    e_varint(N, Bin).

-compile({nowarn_unused_function,e_type_int64/3}).
e_type_int64(Value, Bin, _TrUserData)
    when 0 =< Value, Value =< 127 ->
    <<Bin/binary, Value>>;
e_type_int64(Value, Bin, _TrUserData) ->
    <<N:64/unsigned-native>> = <<Value:64/signed-native>>,
    e_varint(N, Bin).

-compile({nowarn_unused_function,e_type_bool/3}).
e_type_bool(true, Bin, _TrUserData) ->
    <<Bin/binary, 1>>;
e_type_bool(false, Bin, _TrUserData) ->
    <<Bin/binary, 0>>;
e_type_bool(1, Bin, _TrUserData) -> <<Bin/binary, 1>>;
e_type_bool(0, Bin, _TrUserData) -> <<Bin/binary, 0>>.

-compile({nowarn_unused_function,e_type_string/3}).
e_type_string(S, Bin, _TrUserData) ->
    Utf8 = unicode:characters_to_binary(S),
    Bin2 = e_varint(byte_size(Utf8), Bin),
    <<Bin2/binary, Utf8/binary>>.

-compile({nowarn_unused_function,e_type_bytes/3}).
e_type_bytes(Bytes, Bin, _TrUserData)
    when is_binary(Bytes) ->
    Bin2 = e_varint(byte_size(Bytes), Bin),
    <<Bin2/binary, Bytes/binary>>;
e_type_bytes(Bytes, Bin, _TrUserData)
    when is_list(Bytes) ->
    BytesBin = iolist_to_binary(Bytes),
    Bin2 = e_varint(byte_size(BytesBin), Bin),
    <<Bin2/binary, BytesBin/binary>>.

-compile({nowarn_unused_function,e_type_fixed32/3}).
e_type_fixed32(Value, Bin, _TrUserData) ->
    <<Bin/binary, Value:32/little>>.

-compile({nowarn_unused_function,e_type_sfixed32/3}).
e_type_sfixed32(Value, Bin, _TrUserData) ->
    <<Bin/binary, Value:32/little-signed>>.

-compile({nowarn_unused_function,e_type_fixed64/3}).
e_type_fixed64(Value, Bin, _TrUserData) ->
    <<Bin/binary, Value:64/little>>.

-compile({nowarn_unused_function,e_type_sfixed64/3}).
e_type_sfixed64(Value, Bin, _TrUserData) ->
    <<Bin/binary, Value:64/little-signed>>.

-compile({nowarn_unused_function,e_type_float/3}).
e_type_float(V, Bin, _) when is_number(V) ->
    <<Bin/binary, V:32/little-float>>;
e_type_float(infinity, Bin, _) ->
    <<Bin/binary, 0:16, 128, 127>>;
e_type_float('-infinity', Bin, _) ->
    <<Bin/binary, 0:16, 128, 255>>;
e_type_float(nan, Bin, _) ->
    <<Bin/binary, 0:16, 192, 127>>.

-compile({nowarn_unused_function,e_type_double/3}).
e_type_double(V, Bin, _) when is_number(V) ->
    <<Bin/binary, V:64/little-float>>;
e_type_double(infinity, Bin, _) ->
    <<Bin/binary, 0:48, 240, 127>>;
e_type_double('-infinity', Bin, _) ->
    <<Bin/binary, 0:48, 240, 255>>;
e_type_double(nan, Bin, _) ->
    <<Bin/binary, 0:48, 248, 127>>.

-compile({nowarn_unused_function,e_varint/3}).
e_varint(N, Bin, _TrUserData) -> e_varint(N, Bin).

-compile({nowarn_unused_function,e_varint/2}).
e_varint(N, Bin) when N =< 127 -> <<Bin/binary, N>>;
e_varint(N, Bin) ->
    Bin2 = <<Bin/binary, (N band 127 bor 128)>>,
    e_varint(N bsr 7, Bin2).


decode_msg(Bin, MsgName) when is_binary(Bin) ->
    decode_msg(Bin, MsgName, []).

decode_msg(Bin, MsgName, Opts) when is_binary(Bin) ->
    TrUserData = proplists:get_value(user_data, Opts),
    decode_msg_1_catch(Bin, MsgName, TrUserData).

-ifdef('OTP_RELEASE').
decode_msg_1_catch(Bin, MsgName, TrUserData) ->
    try decode_msg_2_doit(MsgName, Bin, TrUserData)
    catch Class:Reason:StackTrace -> error({gpb_error,{decoding_failure, {Bin, MsgName, {Class, Reason, StackTrace}}}})
    end.
-else.
decode_msg_1_catch(Bin, MsgName, TrUserData) ->
    try decode_msg_2_doit(MsgName, Bin, TrUserData)
    catch Class:Reason ->
        StackTrace = erlang:get_stacktrace(),
        error({gpb_error,{decoding_failure, {Bin, MsgName, {Class, Reason, StackTrace}}}})
    end.
-endif.

decode_msg_2_doit('AutResponse', Bin, TrUserData) ->
    id(decode_msg_AutResponse(Bin, TrUserData), TrUserData).



decode_msg_AutResponse(Bin, TrUserData) ->
    dfp_read_field_def_AutResponse(Bin, 0, 0,
				   id(undefined, TrUserData), TrUserData).

dfp_read_field_def_AutResponse(<<8, Rest/binary>>, Z1,
			       Z2, F@_1, TrUserData) ->
    d_field_AutResponse_autResType(Rest, Z1, Z2, F@_1,
				   TrUserData);
dfp_read_field_def_AutResponse(<<>>, 0, 0, F@_1, _) ->
    #'AutResponse'{autResType = F@_1};
dfp_read_field_def_AutResponse(Other, Z1, Z2, F@_1,
			       TrUserData) ->
    dg_read_field_def_AutResponse(Other, Z1, Z2, F@_1,
				  TrUserData).

dg_read_field_def_AutResponse(<<1:1, X:7, Rest/binary>>,
			      N, Acc, F@_1, TrUserData)
    when N < 32 - 7 ->
    dg_read_field_def_AutResponse(Rest, N + 7,
				  X bsl N + Acc, F@_1, TrUserData);
dg_read_field_def_AutResponse(<<0:1, X:7, Rest/binary>>,
			      N, Acc, F@_1, TrUserData) ->
    Key = X bsl N + Acc,
    case Key of
      8 ->
	  d_field_AutResponse_autResType(Rest, 0, 0, F@_1,
					 TrUserData);
      _ ->
	  case Key band 7 of
	    0 ->
		skip_varint_AutResponse(Rest, 0, 0, F@_1, TrUserData);
	    1 -> skip_64_AutResponse(Rest, 0, 0, F@_1, TrUserData);
	    2 ->
		skip_length_delimited_AutResponse(Rest, 0, 0, F@_1,
						  TrUserData);
	    3 ->
		skip_group_AutResponse(Rest, Key bsr 3, 0, F@_1,
				       TrUserData);
	    5 -> skip_32_AutResponse(Rest, 0, 0, F@_1, TrUserData)
	  end
    end;
dg_read_field_def_AutResponse(<<>>, 0, 0, F@_1, _) ->
    #'AutResponse'{autResType = F@_1}.

d_field_AutResponse_autResType(<<1:1, X:7,
				 Rest/binary>>,
			       N, Acc, F@_1, TrUserData)
    when N < 57 ->
    d_field_AutResponse_autResType(Rest, N + 7,
				   X bsl N + Acc, F@_1, TrUserData);
d_field_AutResponse_autResType(<<0:1, X:7,
				 Rest/binary>>,
			       N, Acc, _, TrUserData) ->
    {NewFValue, RestF} = {id(d_enum_AutResponseType(begin
						      <<Res:32/signed-native>> =
							  <<(X bsl N +
							       Acc):32/unsigned-native>>,
						      id(Res, TrUserData)
						    end),
			     TrUserData),
			  Rest},
    dfp_read_field_def_AutResponse(RestF, 0, 0, NewFValue,
				   TrUserData).

skip_varint_AutResponse(<<1:1, _:7, Rest/binary>>, Z1,
			Z2, F@_1, TrUserData) ->
    skip_varint_AutResponse(Rest, Z1, Z2, F@_1, TrUserData);
skip_varint_AutResponse(<<0:1, _:7, Rest/binary>>, Z1,
			Z2, F@_1, TrUserData) ->
    dfp_read_field_def_AutResponse(Rest, Z1, Z2, F@_1,
				   TrUserData).

skip_length_delimited_AutResponse(<<1:1, X:7,
				    Rest/binary>>,
				  N, Acc, F@_1, TrUserData)
    when N < 57 ->
    skip_length_delimited_AutResponse(Rest, N + 7,
				      X bsl N + Acc, F@_1, TrUserData);
skip_length_delimited_AutResponse(<<0:1, X:7,
				    Rest/binary>>,
				  N, Acc, F@_1, TrUserData) ->
    Length = X bsl N + Acc,
    <<_:Length/binary, Rest2/binary>> = Rest,
    dfp_read_field_def_AutResponse(Rest2, 0, 0, F@_1,
				   TrUserData).

skip_group_AutResponse(Bin, FNum, Z2, F@_1,
		       TrUserData) ->
    {_, Rest} = read_group(Bin, FNum),
    dfp_read_field_def_AutResponse(Rest, 0, Z2, F@_1,
				   TrUserData).

skip_32_AutResponse(<<_:32, Rest/binary>>, Z1, Z2, F@_1,
		    TrUserData) ->
    dfp_read_field_def_AutResponse(Rest, Z1, Z2, F@_1,
				   TrUserData).

skip_64_AutResponse(<<_:64, Rest/binary>>, Z1, Z2, F@_1,
		    TrUserData) ->
    dfp_read_field_def_AutResponse(Rest, Z1, Z2, F@_1,
				   TrUserData).

d_enum_AutResponseType(1) -> 'USER_NOT_EXISTS';
d_enum_AutResponseType(2) -> 'WRONG_PW';
d_enum_AutResponseType(3) -> 'LOGGED_IN';
d_enum_AutResponseType(4) -> 'USER_EXISTS';
d_enum_AutResponseType(5) -> 'USER_CREATED';
d_enum_AutResponseType(V) -> V.

read_group(Bin, FieldNum) ->
    {NumBytes, EndTagLen} = read_gr_b(Bin, 0, 0, 0, 0, FieldNum),
    <<Group:NumBytes/binary, _:EndTagLen/binary, Rest/binary>> = Bin,
    {Group, Rest}.

%% Like skipping over fields, but record the total length,
%% Each field is <(FieldNum bsl 3) bor FieldType> ++ <FieldValue>
%% Record the length because varints may be non-optimally encoded.
%%
%% Groups can be nested, but assume the same FieldNum cannot be nested
%% because group field numbers are shared with the rest of the fields
%% numbers. Thus we can search just for an group-end with the same
%% field number.
%%
%% (The only time the same group field number could occur would
%% be in a nested sub message, but then it would be inside a
%% length-delimited entry, which we skip-read by length.)
read_gr_b(<<1:1, X:7, Tl/binary>>, N, Acc, NumBytes, TagLen, FieldNum)
  when N < (32-7) ->
    read_gr_b(Tl, N+7, X bsl N + Acc, NumBytes, TagLen+1, FieldNum);
read_gr_b(<<0:1, X:7, Tl/binary>>, N, Acc, NumBytes, TagLen,
          FieldNum) ->
    Key = X bsl N + Acc,
    TagLen1 = TagLen + 1,
    case {Key bsr 3, Key band 7} of
        {FieldNum, 4} -> % 4 = group_end
            {NumBytes, TagLen1};
        {_, 0} -> % 0 = varint
            read_gr_vi(Tl, 0, NumBytes + TagLen1, FieldNum);
        {_, 1} -> % 1 = bits64
            <<_:64, Tl2/binary>> = Tl,
            read_gr_b(Tl2, 0, 0, NumBytes + TagLen1 + 8, 0, FieldNum);
        {_, 2} -> % 2 = length_delimited
            read_gr_ld(Tl, 0, 0, NumBytes + TagLen1, FieldNum);
        {_, 3} -> % 3 = group_start
            read_gr_b(Tl, 0, 0, NumBytes + TagLen1, 0, FieldNum);
        {_, 4} -> % 4 = group_end
            read_gr_b(Tl, 0, 0, NumBytes + TagLen1, 0, FieldNum);
        {_, 5} -> % 5 = bits32
            <<_:32, Tl2/binary>> = Tl,
            read_gr_b(Tl2, 0, 0, NumBytes + TagLen1 + 4, 0, FieldNum)
    end.

read_gr_vi(<<1:1, _:7, Tl/binary>>, N, NumBytes, FieldNum)
  when N < (64-7) ->
    read_gr_vi(Tl, N+7, NumBytes+1, FieldNum);
read_gr_vi(<<0:1, _:7, Tl/binary>>, _, NumBytes, FieldNum) ->
    read_gr_b(Tl, 0, 0, NumBytes+1, 0, FieldNum).

read_gr_ld(<<1:1, X:7, Tl/binary>>, N, Acc, NumBytes, FieldNum)
  when N < (64-7) ->
    read_gr_ld(Tl, N+7, X bsl N + Acc, NumBytes+1, FieldNum);
read_gr_ld(<<0:1, X:7, Tl/binary>>, N, Acc, NumBytes, FieldNum) ->
    Len = X bsl N + Acc,
    NumBytes1 = NumBytes + 1,
    <<_:Len/binary, Tl2/binary>> = Tl,
    read_gr_b(Tl2, 0, 0, NumBytes1 + Len, 0, FieldNum).

merge_msgs(Prev, New)
    when element(1, Prev) =:= element(1, New) ->
    merge_msgs(Prev, New, element(1, Prev), []).

merge_msgs(Prev, New, MsgName) when is_atom(MsgName) ->
    merge_msgs(Prev, New, MsgName, []);
merge_msgs(Prev, New, Opts)
    when element(1, Prev) =:= element(1, New),
	 is_list(Opts) ->
    merge_msgs(Prev, New, element(1, Prev), Opts).

merge_msgs(Prev, New, MsgName, Opts) ->
    TrUserData = proplists:get_value(user_data, Opts),
    case MsgName of
      'AutResponse' ->
	  merge_msg_AutResponse(Prev, New, TrUserData)
    end.

-compile({nowarn_unused_function,merge_msg_AutResponse/3}).
merge_msg_AutResponse(#'AutResponse'{},
		      #'AutResponse'{autResType = NFautResType}, _) ->
    #'AutResponse'{autResType = NFautResType}.


verify_msg(Msg) when tuple_size(Msg) >= 1 ->
    verify_msg(Msg, element(1, Msg), []);
verify_msg(X) ->
    mk_type_error(not_a_known_message, X, []).

verify_msg(Msg, MsgName) when is_atom(MsgName) ->
    verify_msg(Msg, MsgName, []);
verify_msg(Msg, Opts) when tuple_size(Msg) >= 1 ->
    verify_msg(Msg, element(1, Msg), Opts);
verify_msg(X, _Opts) ->
    mk_type_error(not_a_known_message, X, []).

verify_msg(Msg, MsgName, Opts) ->
    TrUserData = proplists:get_value(user_data, Opts),
    case MsgName of
      'AutResponse' ->
	  v_msg_AutResponse(Msg, [MsgName], TrUserData);
      _ -> mk_type_error(not_a_known_message, Msg, [])
    end.


-compile({nowarn_unused_function,v_msg_AutResponse/3}).
-dialyzer({nowarn_function,v_msg_AutResponse/3}).
v_msg_AutResponse(#'AutResponse'{autResType = F1}, Path,
		  TrUserData) ->
    v_enum_AutResponseType(F1, [autResType | Path],
			   TrUserData),
    ok;
v_msg_AutResponse(X, Path, _TrUserData) ->
    mk_type_error({expected_msg, 'AutResponse'}, X, Path).

-compile({nowarn_unused_function,v_enum_AutResponseType/3}).
-dialyzer({nowarn_function,v_enum_AutResponseType/3}).
v_enum_AutResponseType('USER_NOT_EXISTS', _Path,
		       _TrUserData) ->
    ok;
v_enum_AutResponseType('WRONG_PW', _Path,
		       _TrUserData) ->
    ok;
v_enum_AutResponseType('LOGGED_IN', _Path,
		       _TrUserData) ->
    ok;
v_enum_AutResponseType('USER_EXISTS', _Path,
		       _TrUserData) ->
    ok;
v_enum_AutResponseType('USER_CREATED', _Path,
		       _TrUserData) ->
    ok;
v_enum_AutResponseType(V, Path, TrUserData)
    when is_integer(V) ->
    v_type_sint32(V, Path, TrUserData);
v_enum_AutResponseType(X, Path, _TrUserData) ->
    mk_type_error({invalid_enum, 'AutResponseType'}, X,
		  Path).

-compile({nowarn_unused_function,v_type_sint32/3}).
-dialyzer({nowarn_function,v_type_sint32/3}).
v_type_sint32(N, _Path, _TrUserData)
    when -2147483648 =< N, N =< 2147483647 ->
    ok;
v_type_sint32(N, Path, _TrUserData)
    when is_integer(N) ->
    mk_type_error({value_out_of_range, sint32, signed, 32},
		  N, Path);
v_type_sint32(X, Path, _TrUserData) ->
    mk_type_error({bad_integer, sint32, signed, 32}, X,
		  Path).

-compile({nowarn_unused_function,mk_type_error/3}).
-spec mk_type_error(_, _, list()) -> no_return().
mk_type_error(Error, ValueSeen, Path) ->
    Path2 = prettify_path(Path),
    erlang:error({gpb_type_error,
		  {Error, [{value, ValueSeen}, {path, Path2}]}}).


-compile({nowarn_unused_function,prettify_path/1}).
-dialyzer({nowarn_function,prettify_path/1}).
prettify_path([]) -> top_level;
prettify_path(PathR) ->
    list_to_atom(lists:append(lists:join(".",
					 lists:map(fun atom_to_list/1,
						   lists:reverse(PathR))))).


-compile({nowarn_unused_function,id/2}).
-compile({inline,id/2}).
id(X, _TrUserData) -> X.

-compile({nowarn_unused_function,v_ok/3}).
-compile({inline,v_ok/3}).
v_ok(_Value, _Path, _TrUserData) -> ok.

-compile({nowarn_unused_function,m_overwrite/3}).
-compile({inline,m_overwrite/3}).
m_overwrite(_Prev, New, _TrUserData) -> New.

-compile({nowarn_unused_function,cons/3}).
-compile({inline,cons/3}).
cons(Elem, Acc, _TrUserData) -> [Elem | Acc].

-compile({nowarn_unused_function,lists_reverse/2}).
-compile({inline,lists_reverse/2}).
'lists_reverse'(L, _TrUserData) -> lists:reverse(L).
-compile({nowarn_unused_function,'erlang_++'/3}).
-compile({inline,'erlang_++'/3}).
'erlang_++'(A, B, _TrUserData) -> A ++ B.


get_msg_defs() ->
    [{{enum, 'AutResponseType'},
      [{'USER_NOT_EXISTS', 1}, {'WRONG_PW', 2},
       {'LOGGED_IN', 3}, {'USER_EXISTS', 4},
       {'USER_CREATED', 5}]},
     {{msg, 'AutResponse'},
      [#field{name = autResType, fnum = 1, rnum = 2,
	      type = {enum, 'AutResponseType'}, occurrence = required,
	      opts = []}]}].


get_msg_names() -> ['AutResponse'].


get_group_names() -> [].


get_msg_or_group_names() -> ['AutResponse'].


get_enum_names() -> ['AutResponseType'].


fetch_msg_def(MsgName) ->
    case find_msg_def(MsgName) of
      Fs when is_list(Fs) -> Fs;
      error -> erlang:error({no_such_msg, MsgName})
    end.


fetch_enum_def(EnumName) ->
    case find_enum_def(EnumName) of
      Es when is_list(Es) -> Es;
      error -> erlang:error({no_such_enum, EnumName})
    end.


find_msg_def('AutResponse') ->
    [#field{name = autResType, fnum = 1, rnum = 2,
	    type = {enum, 'AutResponseType'}, occurrence = required,
	    opts = []}];
find_msg_def(_) -> error.


find_enum_def('AutResponseType') ->
    [{'USER_NOT_EXISTS', 1}, {'WRONG_PW', 2},
     {'LOGGED_IN', 3}, {'USER_EXISTS', 4},
     {'USER_CREATED', 5}];
find_enum_def(_) -> error.


enum_symbol_by_value('AutResponseType', Value) ->
    enum_symbol_by_value_AutResponseType(Value).


enum_value_by_symbol('AutResponseType', Sym) ->
    enum_value_by_symbol_AutResponseType(Sym).


enum_symbol_by_value_AutResponseType(1) ->
    'USER_NOT_EXISTS';
enum_symbol_by_value_AutResponseType(2) -> 'WRONG_PW';
enum_symbol_by_value_AutResponseType(3) -> 'LOGGED_IN';
enum_symbol_by_value_AutResponseType(4) ->
    'USER_EXISTS';
enum_symbol_by_value_AutResponseType(5) ->
    'USER_CREATED'.


enum_value_by_symbol_AutResponseType('USER_NOT_EXISTS') ->
    1;
enum_value_by_symbol_AutResponseType('WRONG_PW') -> 2;
enum_value_by_symbol_AutResponseType('LOGGED_IN') -> 3;
enum_value_by_symbol_AutResponseType('USER_EXISTS') ->
    4;
enum_value_by_symbol_AutResponseType('USER_CREATED') ->
    5.


get_service_names() -> [].


get_service_def(_) -> error.


get_rpc_names(_) -> error.


find_rpc_def(_, _) -> error.



-spec fetch_rpc_def(_, _) -> no_return().
fetch_rpc_def(ServiceName, RpcName) ->
    erlang:error({no_such_rpc, ServiceName, RpcName}).


%% Convert a a fully qualified (ie with package name) service name
%% as a binary to a service name as an atom.
-spec fqbin_to_service_name(_) -> no_return().
fqbin_to_service_name(X) ->
    error({gpb_error, {badservice, X}}).


%% Convert a service name as an atom to a fully qualified
%% (ie with package name) name as a binary.
-spec service_name_to_fqbin(_) -> no_return().
service_name_to_fqbin(X) ->
    error({gpb_error, {badservice, X}}).


%% Convert a a fully qualified (ie with package name) service name
%% and an rpc name, both as binaries to a service name and an rpc
%% name, as atoms.
-spec fqbins_to_service_and_rpc_name(_, _) -> no_return().
fqbins_to_service_and_rpc_name(S, R) ->
    error({gpb_error, {badservice_or_rpc, {S, R}}}).


%% Convert a service name and an rpc name, both as atoms,
%% to a fully qualified (ie with package name) service name and
%% an rpc name as binaries.
-spec service_and_rpc_name_to_fqbins(_, _) -> no_return().
service_and_rpc_name_to_fqbins(S, R) ->
    error({gpb_error, {badservice_or_rpc, {S, R}}}).


fqbin_to_msg_name(<<"authentication.AutResponse">>) -> 'AutResponse';
fqbin_to_msg_name(E) -> error({gpb_error, {badmsg, E}}).


msg_name_to_fqbin('AutResponse') -> <<"authentication.AutResponse">>;
msg_name_to_fqbin(E) -> error({gpb_error, {badmsg, E}}).


fqbin_to_enum_name(<<"authentication.AutResponseType">>) -> 'AutResponseType';
fqbin_to_enum_name(E) ->
    error({gpb_error, {badenum, E}}).


enum_name_to_fqbin('AutResponseType') -> <<"authentication.AutResponseType">>;
enum_name_to_fqbin(E) ->
    error({gpb_error, {badenum, E}}).


get_package_name() -> authentication.


%% Whether or not the message names
%% are prepended with package name or not.
uses_packages() -> false.


source_basename() -> "autresponse.proto".


%% Retrieve all proto file names, also imported ones.
%% The order is top-down. The first element is always the main
%% source file. The files are returned with extension,
%% see get_all_proto_names/0 for a version that returns
%% the basenames sans extension
get_all_source_basenames() -> ["autresponse.proto"].


%% Retrieve all proto file names, also imported ones.
%% The order is top-down. The first element is always the main
%% source file. The files are returned sans .proto extension,
%% to make it easier to use them with the various get_xyz_containment
%% functions.
get_all_proto_names() -> ["autresponse"].


get_msg_containment("autresponse") -> ['AutResponse'];
get_msg_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_pkg_containment("autresponse") -> undefined;
get_pkg_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_service_containment("autresponse") -> [];
get_service_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_rpc_containment("autresponse") -> [];
get_rpc_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_enum_containment("autresponse") ->
    ['AutResponseType'];
get_enum_containment(P) ->
    error({gpb_error, {badproto, P}}).


get_proto_by_msg_name_as_fqbin(<<"authentication.AutResponse">>) ->
    "autresponse";
get_proto_by_msg_name_as_fqbin(E) ->
    error({gpb_error, {badmsg, E}}).


-spec get_proto_by_service_name_as_fqbin(_) -> no_return().
get_proto_by_service_name_as_fqbin(E) ->
    error({gpb_error, {badservice, E}}).


get_proto_by_enum_name_as_fqbin(<<"authentication.AutResponseType">>) ->
    "autresponse";
get_proto_by_enum_name_as_fqbin(E) ->
    error({gpb_error, {badenum, E}}).


-spec get_protos_by_pkg_name_as_fqbin(_) -> no_return().
get_protos_by_pkg_name_as_fqbin(E) ->
    error({gpb_error, {badpkg, E}}).



gpb_version_as_string() ->
    "4.11.0".

gpb_version_as_list() ->
    [4,11,0].