package menus;

import main.Client;

import java.io.IOException;
import java.util.Scanner;

public class MMainMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- MANUFACTURER MAIN MENU -----");
        System.out.println("1. Enviar oferta de produção.");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        int choice = s.nextInt();
        if (choice == 1) {
            Client.publisher.sendMessage("Tecnologia", "PC");

            Menu menu = new MMainMenu();
            menu.run();
        }
    }
}
