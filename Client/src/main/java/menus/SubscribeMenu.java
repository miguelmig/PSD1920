package menus;

import main.Client;

import java.io.IOException;
import java.time.Year;
import java.util.Scanner;

public class SubscribeMenu extends Menu {
    @Override
    public void display() {
        System.out.println("----- SUBSCRIBE MENU -----");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        System.out.print("Manufacturer: ");
        String manufacturer = s.nextLine();

        Client.subscriber.addSubscription(manufacturer);

        System.out.println("Subscription added!");

        Menu mainMenu = new IMainMenu();
        mainMenu.run();
    }
}
