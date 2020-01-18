package menus;

import java.util.Scanner;

public class StartMenu extends Menu {


    @Override
    public void display() {
        System.out.println("----- START MENU -----");
        System.out.println("1. Login");
        System.out.println("2. Register");
    }

    @Override
    public void handleEvents() {
        Scanner s = new Scanner(System.in);

        int choice = s.nextInt();
        switch (choice) {
            case 1:
                Menu loginMenu = new LoginMenu();
                loginMenu.run();
                break;

            case 2:
                Menu registerMenu = new RegisterMenu();
                registerMenu.run();
                break;
        }
    }
}
