package main;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.List;

public class Subscriber extends Thread {

    private ZMQ.Context context;
    private ZMQ.Socket socket;

    private List<String> subscriptions;

    public Subscriber(String area, List<String> topics) {
        this.context = ZMQ.context(1);
        this.socket = context.socket(SocketType.SUB);
        switch (area) {
            case "tecnologia":
                socket.connect("tcp://localhost:8002");
                break;

            case "alimentacao":
                socket.connect("tcp://localhost:8007");
                break;

            case "texteis":
                socket.connect("tcp://localhost:8012");
                break;

            case "diversos":
                socket.connect("tcp://localhost:8017");
                break;

            default:
                break;
        }
        this.subscriptions = topics;

        for (String topic : subscriptions) {
            socket.subscribe(topic);
        }
    }

    public void addSubscription(String topic) {
        subscriptions.add(topic);
        socket.subscribe(topic);
    }

    public void run() {

        System.out.println("Subscriber running");

        while (true) {
            byte[] b = socket.recv();
            System.out.println("Recebi: " + new String(b));
        }

    }
}
