package menus;

import main.Client;

import java.io.IOException;
import java.util.Scanner;

public class ProductMenu extends Menu {


    @Override
    public void display() {
        System.out.println("----- PRODUCT MENU -----");
        System.out.println("Make product offer:");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        System.out.print("Product name: ");
        String name = s.nextLine();

        System.out.print("Minimum quantity: ");
        int minimumQuantity = s.nextInt();

        System.out.print("Maximum quantity: ");
        int maximumQuantity = s.nextInt();

        System.out.print("Unitary price: ");
        int unitaryPrice = s.nextInt();

        System.out.print("Negotiation Time: ");
        int negotiationTime = s.nextInt();

        Client.sendProductMessage(
                name,
                minimumQuantity,
                maximumQuantity,
                unitaryPrice,
                negotiationTime);

        String manufacturer = Client.user.getUsername();
        Client.publisher.sendMessage(manufacturer, name);

        System.out.println("Product offer sent!");

        Menu mainMenu = new MMainMenu();
        mainMenu.run();
    }
}
