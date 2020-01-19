package main;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class Publisher {

    private ZMQ.Socket socket;

    public Publisher() {
        ZMQ.Context context = ZMQ.context(1);
        this.socket = context.socket(SocketType.PUB);
        socket.connect("tcp://localhost:12345");
    }

    public void sendMessage(String topic, String msg) {
        socket.sendMore(topic);
        socket.send(msg);
    }
}
