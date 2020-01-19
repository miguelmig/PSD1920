package menus;

import main.Client;
import models.Importer;
import protos.authentication.AuthenticationReply;

import java.io.IOException;
import java.util.Scanner;

public class RegisterMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- REGISTER MENU -----");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        System.out.print("username: ");
        String username = s.nextLine();

        System.out.print("password: ");
        String password = s.nextLine();

        Client.sendAuthenticationRequest(
                Authentication.AuthenticationRequestType.REGISTER,
                Client.getClientType(),
                username,
                password
        );

        AuthenticationReply.AutResponse reply = Client.readAuthenticationReply();
        AuthenticationReply.AutResponseType replyType = reply.getAutResType();
        switch (replyType) {
            case USER_EXISTS:
                System.out.println("Error: User already exists.");
                Menu registerMenu = new RegisterMenu();
                registerMenu.run();
                break;

            case USER_CREATED:
                System.out.println("Successfully registered!");

                Client.user.setUsername(username);
                Client.user.setPassword(password);

                if (Client.user instanceof Importer) {
                    Menu menu = new IMainMenu();
                    menu.run();
                } else {
                    Menu menu = new MMainMenu();
                    menu.run();
                }
                break;

            default:
                break;
        }
    }
}
