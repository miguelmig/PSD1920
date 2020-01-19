package menus;

import java.io.IOException;
import java.util.Scanner;

public class IMainMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- IMPORTER MAIN MENU -----");
        System.out.println("1. Send order.");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        int choice = s.nextInt();
        if (choice == 1) {
            Menu orderMenu = new OrderMenu();
            orderMenu.run();
        } else {
            System.out.println("Invalid input.");
            this.run();
        }
    }
}
