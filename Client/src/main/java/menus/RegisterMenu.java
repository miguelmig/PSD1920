package menus;

import main.Client;
import protos.authentication.Authentication;

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
                Authentication.AuthenticationRequestType.LOGIN,
                Client.getClientType(),
                username,
                password
        );

        Client.user.setUsername(username);
        Client.user.setPassword(password);
    }
}
