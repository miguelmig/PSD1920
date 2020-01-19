package main;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class Broker {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket pubs = context.socket(SocketType.XSUB);
        ZMQ.Socket subs = context.socket(SocketType.XPUB);

        pubs.bind("tcp://*:12345");
        subs.bind("tcp://*:54321");

        System.out.println("Broker is running!");
        new Proxy(context, pubs, subs).poll();
    }
}
