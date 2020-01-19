package menus;

import java.io.IOException;
import java.util.Scanner;

public class StartMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- START MENU -----");
        System.out.println("1. Register.");
        System.out.println("2. Login.");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        int choice = s.nextInt();
        switch (choice) {
            case 1:
                Menu registerMenu = new RegisterMenu();
                registerMenu.run();
                break;

            case 2:
                Menu loginMenu = new LoginMenu();
                loginMenu.run();
                break;

            default:
                System.out.println("Invalid input");
                this.run();
                break;
        }
    }
}
