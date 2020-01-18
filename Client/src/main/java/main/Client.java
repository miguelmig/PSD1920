package main;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import models.Importer;
import models.Manufacturer;
import models.User;
import protos.authentication.Authentication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Client {

    private static User user;
    private static CodedInputStream cis;
    private static CodedOutputStream cos;

    public static void main(String[] args) throws IOException {

        //if (args.length != 1) {
        //    System.err.println("Error: Invalid number of arguments.");
        //    System.err.println("Must be: /client client-type");
        //}

        Socket socket = new Socket("localhost", 13000);

        cis = CodedInputStream.newInstance(socket.getInputStream());
        cos = CodedOutputStream.newInstance(socket.getOutputStream());

        Authentication.AuthenticationRequestType authType =
                Authentication.AuthenticationRequestType.REGISTER;

        Authentication.ClientType clientType =
                Authentication.ClientType.IMPORTER;

        String username = "pedro";
        String password = "pedro";

        sendAuthenticationRequest(authType, clientType, username, password);
        while (true) {}
    }


    public static void sendAuthenticationRequest(
            Authentication.AuthenticationRequestType authType,
            Authentication.ClientType clientType,
            String username,
            String password) throws IOException {

        Authentication.AuthenticationRequest.Builder request =
                Authentication.AuthenticationRequest.newBuilder();

        request.setAuthType(authType);
        request.setClientType(clientType);
        request.setUsername(username);
        request.setPassword(password);

        request.build().writeTo(cos);
        cos.flush();
    }
}
