package menus;

import main.Client;
import protos.authentication.Authentication;

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

        // TODO wait from reply from server

        
    }
}
