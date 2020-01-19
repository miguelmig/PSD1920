
import net.FrontEndHandler;
import net.NotificationBroker;
import rest.RESTClient;

public class Negociador {
    public static void main(String[] args) throws Exception {
        if(args.length < 1){
            System.err.println("Not enough arguments!");
            System.err.print("Usage: <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        new Thread(() -> {
            net.NotificationBroker notification_broker = new NotificationBroker(port);
            notification_broker.poll();
        }).start();

        System.out.println("[*] Starting REST Client...");
        RESTClient rest = new RESTClient();

        System.out.println("[*] Starting Front End Handler...");
        FrontEndHandler handler = new FrontEndHandler(port, rest);
        handler.run();
    }
}
