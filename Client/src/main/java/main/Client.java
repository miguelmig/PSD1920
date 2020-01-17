package main;

import models.Importer;
import models.Manufacturer;
import models.User;

public class Client {

    public static User user;

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Error: Invalid number of arguments.");
            System.err.println("Must be: /client client-type");
        }

        String clientType = args[0];
        switch (clientType) {
            case "importer":
                break;

            case "manufacturer":
                break;

            default:
                System.err.println("Error: Invalid client type.");
                return;
        }



    }
}
