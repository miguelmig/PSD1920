package main;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class Publisher {

    private ZMQ.Socket socket;

    public Publisher(String area) {
        ZMQ.Context context = ZMQ.context(1);
        this.socket = context.socket(SocketType.PUB);

        switch (area) {
            case "tecnologia":
                socket.connect("tcp://localhost:8001");
                break;

            case "alimentacao":
                socket.connect("tcp://localhost:8006");
                break;

            case "texteis":
                socket.connect("tcp://localhost:8011");
                break;

            case "diversos":
                socket.connect("tcp://localhost:8016");
                break;

            default:
                break;
        }

        socket.connect("tcp://localhost:12345");
    }

    public void sendMessage(String topic, String msg) {
        socket.sendMore(topic);
        socket.send(msg);
    }
}
