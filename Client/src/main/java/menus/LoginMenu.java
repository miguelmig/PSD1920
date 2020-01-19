package menus;

import main.Client;
import protos.authentication.Authentication;
import protos.authentication.AuthenticationReply;

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
                Authentication.AuthenticationRequestType.LOGIN,
                Client.getClientType(),
                username,
                password
        );

        AuthenticationReply.AutResponse reply = Client.readAuthenticationReply();
        AuthenticationReply.AutResponseType replyType = reply.getAutResType();

        switch (replyType) {
            case USER_NOT_EXISTS:
                System.out.println("Error: User doesn't exist.");
                Menu loginMenu = new LoginMenu();
                loginMenu.run();
                break;

            case WRONG_PW:
                System.out.println("Error: Incorrect password.");
                loginMenu = new LoginMenu();
                loginMenu.run();
                break;

            case LOGGED_IN:
                System.out.println("Successful login!");
            default:
                break;
        }
    }
}
