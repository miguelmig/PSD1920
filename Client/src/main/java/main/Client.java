package main;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import menus.Menu;
import menus.StartMenu;
import models.Importer;
import models.Manufacturer;
import models.User;
import protos.authentication.Authentication;
import protos.authentication.AuthenticationReply;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Client {

    public static User user;
    private static CodedInputStream cis;
    private static CodedOutputStream cos;

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Error: Invalid number of arguments.");
            System.err.println("Must be: /client client-type");
            return;
        }

        Socket socket = new Socket("localhost", 13001);

        cis = CodedInputStream.newInstance(socket.getInputStream());
        cos = CodedOutputStream.newInstance(socket.getOutputStream());

        System.out.println("Successful connection!");

        switch (args[0]) {
            case "importer":
                user = new Importer();
                break;

            case "manufacturer":
                user = new Manufacturer();
                break;

            default:
                System.err.println("Error: Invalid client type.");
                return;
        }

        Menu menu = new StartMenu();
        menu.run();
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

        //System.out.println(Arrays.toString(cis.readByteArray()));
    }

    public static AuthenticationReply.AutResponse readAuthenticationReply()
            throws IOException {

<<<<<<< HEAD
        int len = cis.readRawLittleEndian32();
        return AuthenticationReply.AutResponse.parseFrom(cis.readRawBytes(len));

=======
        //System.out.println(Arrays.toString(cis.readByteArray()));
        return AuthenticationReply.AutResponse.parseFrom(cis);
>>>>>>> 2cdbd3494216f92c881d70bdc06911c1a8f5a7a8
    }

    public static Authentication.ClientType getClientType() {

        if (user instanceof Importer) {
            return Authentication.ClientType.IMPORTER;
        } else {
            return Authentication.ClientType.MANUFACTURER;
        }
    }


}
