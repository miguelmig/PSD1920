package menus;

import main.Client;

import java.io.IOException;
import java.util.Scanner;

public class MMainMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- MANUFACTURER MAIN MENU -----");
        System.out.println("1. Send product offer.");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        int choice = s.nextInt();
        if (choice == 1) {
            Menu productMenu = new ProductMenu();
            productMenu.run();
        } else {
            this.run();
        }
    }
}
