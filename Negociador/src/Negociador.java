
import net.FrontEndHandler;
import net.NotificationBroker;
import rest.RESTClient;

public class Negociador {
    public static void main(String[] args) throws Exception {
        if(args.length < 2){
            System.err.println("Not enough arguments!");
            System.err.print("Usage: " + args[0] + "<port>");
            return;
        }

        int port = Integer.parseInt(args[1]);

        new Thread(() -> {
            net.NotificationBroker notification_broker = new NotificationBroker(port);
            notification_broker.poll();
        });

        System.out.println("[*] Starting REST Client...");
        RESTClient rest = new RESTClient();

        System.out.println("[*] Starting Front End Handler...");
        FrontEndHandler handler = new FrontEndHandler(port, rest);
        handler.run();
    }
}