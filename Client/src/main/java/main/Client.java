package main;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import menus.Menu;
import menus.StartMenu;
import models.Importer;
import models.Manufacturer;
import models.User;
import protos.authentication.AuthenticationReply;
import protos.message.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {

    public static User user;

    public static Publisher publisher;
    public static Subscriber subscriber;
    public static Listener listener;

    private static CodedInputStream cis;
    private static CodedOutputStream cos;

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.err.println("Error: Invalid number of arguments.");
            System.err.println("Must be: /client importer area topic1 topic2 ...");
            System.err.println("         /client manufacturer area");
            return;
        }

        List<String> areas = new ArrayList<>() {{
            add("tecnologia");
            add("alimentacao");
            add("texteis");
            add("diversos");
        }};

        String area = args[1];
        if (!areas.contains(area)) {
            System.err.println("Error: Invalid area.");
            System.err.println("Areas: tecnologia alimentacao texteis diversos");
            return;
        }

        Socket socket = new Socket("localhost", 13000);

        cis = CodedInputStream.newInstance(socket.getInputStream());
        cos = CodedOutputStream.newInstance(socket.getOutputStream());

        System.out.println("Successful connection!");

        switch (args[0]) {
            case "importer":
                List<String> topics = new ArrayList<>();
                topics.addAll(Arrays.asList(args).subList(2, args.length));

                user = new Importer();
                user.setArea(area);
                subscriber = new Subscriber(area, topics);
                listener = new Listener(cis);
                break;

            case "manufacturer":
                user = new Manufacturer();
                user.setArea(area);
                publisher = new Publisher(area);
                listener = new Listener(cis);
                break;

            default:
                System.err.println("Error: Invalid client type.");
                return;
        }

        Menu menu = new StartMenu();
        menu.run();
    }


    public static void sendAuthenticationRequest(
            Message.AuthenticationRequestType authType,
            Message.ClientType clientType,
            String username,
            String password) throws IOException {

        Message.AuthenticationRequest.Builder request =
                Message.AuthenticationRequest.newBuilder();

        request.setAuthType(authType);
        request.setClientType(clientType);

        String area = user.getArea();
        Message.Area a = null;
        switch (area) {
            case "tecnologia":
                a = Message.Area.TECNOLOGIA;
                break;

            case "alimentacao":
                a = Message.Area.ALIMENTACAO;
                break;

            case "texteis":
                a = Message.Area.TEXTEIS;
                break;

            case "diversos":
                a = Message.Area.DIVERSOS;
                break;
        }

        request.setArea(a);
        request.setUsername(username);
        request.setPassword(password);

        Message.GenericMessage.Builder message = Message.GenericMessage.newBuilder();
        message.setType(Message.GenericMessage.MessageType.AUTH_REQUEST);
        message.setAuthRequest(request);

        message.build().writeTo(cos);
        cos.flush();
    }

    public static AuthenticationReply.AutResponse readAuthenticationReply()
            throws IOException {

        int len = cis.readRawLittleEndian32();
        return AuthenticationReply.AutResponse.parseFrom(cis.readRawBytes(len));
    }

    public static Message.ClientType getClientType() {

        if (user instanceof Importer) {
            return Message.ClientType.IMPORTER;
        } else {
            return Message.ClientType.MANUFACTURER;
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
        order.setImporterName(user.getUsername());
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
