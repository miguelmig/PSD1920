package menus;

import main.Client;
import models.Importer;
import protos.authentication.AuthenticationReply;
import protos.message.Message;

import java.io.IOException;
import java.util.Scanner;

public class LoginMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- LOGIN MENU -----");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        System.out.print("username: ");
        String username = s.nextLine();

        System.out.print("password: ");
        String password = s.nextLine();

        Client.sendAuthenticationRequest(
                Message.AuthenticationRequestType.LOGIN,
                Client.getClientType(),
                username,
                password
        );

        AuthenticationReply.AutResponse reply = Client.readAuthenticationReply();
        AuthenticationReply.AutResponseType replyType = reply.getAutResType();
        switch (replyType) {
            case USER_NOT_EXISTS:
            case WRONG_PW:
                System.out.println("Error: Invalid username or password.");
                Menu startMenu = new StartMenu();
                startMenu.run();
                break;

            case LOGGED_IN:
                System.out.println("Successfully logged in!");
                Client.user.setUsername(username);
                if (Client.user instanceof Importer) {
                    Menu menu = new IMainMenu();
                    Client.subscriber.start();
                    Client.listener.start();
                    menu.run();
                } else {
                    Menu menu = new MMainMenu();
                    Client.listener.start();
                    menu.run();
                }
                break;

            default:
                break;
        }
    }
}
