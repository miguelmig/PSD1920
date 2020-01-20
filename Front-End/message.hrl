%% -*- coding: utf-8 -*-
%% Automatically generated, do not edit
%% Generated by gpb_compile version 4.11.0

-ifndef(message).
-define(message, true).

-define(message_gpb_version, "4.11.0").

-ifndef('GENERICMESSAGE_PB_H').
-define('GENERICMESSAGE_PB_H', true).
-record('GenericMessage',
        {type                   :: 'ADD_ARTIGO' | 'ADD_ENCOMENDA' | 'AUTH_REQUEST' | integer(), % = 1, enum GenericMessage.MessageType
         payload                :: {artigo, message:'AddArtigoMessage'()} | {encomenda, message:'AddEncomendaMessage'()} | {auth_request, message:'AuthenticationRequest'()} | undefined % oneof
        }).
-endif.

-ifndef('ADDARTIGOMESSAGE_PB_H').
-define('ADDARTIGOMESSAGE_PB_H', true).
-record('AddArtigoMessage',
        {manufacturer_name      :: iodata(),        % = 1
         product_name           :: iodata(),        % = 2
         minimum_quantity       :: integer(),       % = 3, 32 bits
         maximum_quantity       :: integer(),       % = 4, 32 bits
         unitary_price          :: integer(),       % = 5, 32 bits
         negotiation_time       :: integer()        % = 6, 32 bits
        }).
-endif.

-ifndef('ADDENCOMENDAMESSAGE_PB_H').
-define('ADDENCOMENDAMESSAGE_PB_H', true).
-record('AddEncomendaMessage',
        {importer_name          :: iodata(),        % = 1
         manufacturer           :: iodata(),        % = 2
         product                :: iodata(),        % = 3
         quantity               :: integer(),       % = 4, 32 bits
         willing_price          :: integer()        % = 5, 32 bits
        }).
-endif.

-ifndef('AUTHENTICATIONREQUEST_PB_H').
-define('AUTHENTICATIONREQUEST_PB_H', true).
-record('AuthenticationRequest',
        {authType               :: 'REGISTER' | 'LOGIN' | integer(), % = 1, enum AuthenticationRequestType
         clientType             :: 'IMPORTER' | 'MANUFACTURER' | integer(), % = 2, enum ClientType
         area                   :: 'TECNOLOGIA' | 'ALIMENTACAO' | 'TEXTEIS' | 'DIVERSOS' | integer(), % = 3, enum Area
         username               :: iodata(),        % = 4
         password               :: iodata()         % = 5
        }).
-endif.

-endif.