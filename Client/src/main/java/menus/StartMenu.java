package menus;

import java.io.IOException;
import java.util.Scanner;

public class StartMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- START MENU -----");
        System.out.println("1. Register.");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        int choice = s.nextInt();
        if (choice == 1) {
            Menu registerMenu = new RegisterMenu();
            registerMenu.run();
        }
    }
}
