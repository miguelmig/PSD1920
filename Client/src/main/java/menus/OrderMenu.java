package menus;

import main.Client;

import java.io.IOException;
import java.util.Scanner;

public class OrderMenu extends Menu {


    @Override
    public void display() {
        System.out.println("----- ORDER MENU -----");
        System.out.println("Make order:");
    }

    @Override
    public void handleEvents() throws IOException {
        Scanner s = new Scanner(System.in);

        System.out.print("Manufacturer name: ");
        String manufacturer = s.nextLine();

        System.out.print("Product name: ");
        String product = s.nextLine();

        System.out.print("Quantity: ");
        int quantity = s.nextInt();

        System.out.print("Price willing to pay: ");
        int willingPrice = s.nextInt();

        Client.sendOrderMessage(manufacturer, product, quantity, willingPrice);

        System.out.println("Order sent!");

        Menu mainMenu = new IMainMenu();
        mainMenu.run();
    }
}
