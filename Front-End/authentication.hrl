%% -*- coding: utf-8 -*-
%% Automatically generated, do not edit
%% Generated by gpb_compile version 4.11.0

-ifndef(authentication).
-define(authentication, true).

-define(authentication_gpb_version, "4.11.0").

-ifndef('AUTHENTICATIONREQUEST_PB_H').
-define('AUTHENTICATIONREQUEST_PB_H', true).
-record('AuthenticationRequest',
        {authType               :: 'REGISTER' | 'LOGIN' | integer(), % = 1, enum AuthenticationRequestType
         clientType             :: 'IMPORTER' | 'MANUFACTURER' | integer(), % = 2, enum ClientType
         username               :: iodata(),        % = 3
         password               :: iodata()         % = 4
        }).
-endif.

-endif.
