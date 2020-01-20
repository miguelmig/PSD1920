package menus;

import java.io.IOException;
import java.util.Scanner;

public class IMainMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- IMPORTER MAIN MENU -----");
        System.out.println("1. Send order.");
        System.out.println("2. Subscribe to manufacturer.");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        int choice = s.nextInt();
        switch (choice) {
            case 1:
                Menu orderMenu = new OrderMenu();
                orderMenu.run();
                break;

            case 2:
                Menu subscribeMenu = new SubscribeMenu();
                subscribeMenu.run();
                break;

            default:
                System.out.println("Invalid input.");
                this.run();
                break;
        }
    }
}
