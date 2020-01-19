package menus;

import java.io.IOException;

public class IMainMenu extends Menu {

    @Override
    public void display() {
        System.out.println("----- IMPORTER MAIN MENU -----");
        System.out.println("1. Enviar oferta de encomenda");

    }

    @Override
    public void handleEvents() throws IOException {

    }
}
