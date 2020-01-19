package menus;

import java.io.IOException;
import java.util.Scanner;

public class IMainMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- IMPORTER MAIN MENU -----");
        System.out.println("1. Enviar oferta de encomenda.");
        System.out.println("2. Ver fabricantes de interesse.");
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
                // TODO menu lista de fabricantes
                break;

            default:
                break;
        }
    }
}
