package menus;

import java.io.IOException;

public abstract class Menu {

    public abstract void display();

    public abstract void handleEvents() throws IOException;

    public void run() throws IOException {
        this.display();
        this.handleEvents();
    }
}
