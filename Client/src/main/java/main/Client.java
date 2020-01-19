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
import protos.message.Message;
import protos.order.Order;
import protos.product.Product;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Client {

    public static User user;

    public static Publisher publisher;
    public static Subscriber subscriber;

    private static CodedInputStream cis;
    private static CodedOutputStream cos;

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Error: Invalid number of arguments.");
            System.err.println("Must be: /client client-type");
            return;
        }

        Socket socket = new Socket("localhost", 13000);

        cis = CodedInputStream.newInstance(socket.getInputStream());
        cos = CodedOutputStream.newInstance(socket.getOutputStream());

        System.out.println("Successful connection!");

        switch (args[0]) {
            case "importer":
                user = new Importer();
                subscriber = new Subscriber();
                break;

            case "manufacturer":
                user = new Manufacturer();
                publisher = new Publisher();
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
    }

    public static AuthenticationReply.AutResponse readAuthenticationReply()
            throws IOException {

        int len = cis.readRawLittleEndian32();
        return AuthenticationReply.AutResponse.parseFrom(cis.readRawBytes(len));
    }

    public static Authentication.ClientType getClientType() {

        if (user instanceof Importer) {
            return Authentication.ClientType.IMPORTER;
        } else {
            return Authentication.ClientType.MANUFACTURER;
        }
    }

    public static void sendProductMessage(
            String name,
            int minimumQuantity,
            int maximumQuantity,
            int unitaryPrice,
            int negotiationTime) throws IOException {


        Message.AddArtigoMessage.Builder product = Message.AddArtigoMessage.newBuilder();
        product.setManufacturerName(user.getUsername());
        product.setProductName(name);
        product.setMinimumQuantity(minimumQuantity);
        product.setMaximumQuantity(maximumQuantity);
        product.setUnitaryPrice(unitaryPrice);
        product.setNegotiationTime(negotiationTime);

        Message.GenericMessage.Builder message = Message.GenericMessage.newBuilder();
        message.setType(Message.GenericMessage.MessageType.ADD_ARTIGO);
        message.setArtigo(product);

        message.build().writeTo(cos);
        cos.flush();
    }


    public static void sendOrderMessage(
            String manufacturer,
            String product,
            int quantity,
            int willingPrice) throws IOException {


        Message.AddEncomendaMessage.Builder order = Message.AddEncomendaMessage.newBuilder();
        order.setManufacturer(manufacturer);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setWillingPrice(willingPrice);

        Message.GenericMessage.Builder message = Message.GenericMessage.newBuilder();
        message.setType(Message.GenericMessage.MessageType.ADD_ENCOMENDA);
        message.setEncomenda(order);

        message.build().writeTo(cos);
        cos.flush();
    }

}
