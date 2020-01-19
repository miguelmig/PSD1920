package main;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.List;

public class Subscriber extends Thread {

    private ZMQ.Context context;
    private ZMQ.Socket socket;

    private List<String> subscriptions;

    public Subscriber() {
        this.context = ZMQ.context(1);
        this.socket = context.socket(SocketType.SUB);
        socket.connect("tcp://localhost:54321");

        this.subscriptions = new ArrayList<>();
    }

    public void addSubscription(String topic) {
        subscriptions.add(topic);
        socket.subscribe(topic);
    }

    public void run() {
        System.out.println("Subscriber is running!");

        addSubscription("Tecnologia");

        while (true) {
            byte[] b = socket.recv();
            System.out.println("Recebi: " + new String(b));
        }
    }
}
