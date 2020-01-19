package net;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class NotificationBroker {

    private ZMQ.Poller items;
    private ZMQ.Socket pubs, subs;

    public NotificationBroker(int port)
    {
        ZMQ.Context context = ZMQ.context(1);

        this.pubs = context.socket(SocketType.XSUB);
        this.subs = context.socket(SocketType.XPUB);

        pubs.bind("tcp://*:" + (port + 1));
        subs.bind("tcp://*:" + (port + 2));

        this.items = context.poller(2);
        this.items.register(pubs, ZMQ.Poller.POLLIN);
        this.items.register(subs, ZMQ.Poller.POLLIN);

        System.out.println("Broker is running!");

    }

    public void poll() {
        while (!Thread.currentThread().isInterrupted()) {
            items.poll();

            ZMQ.Socket from, to;
            if (items.pollin(0)) {
                from = pubs; to = subs;
            } else {
                from = subs; to = pubs;
            }

            while (true) {
                byte[] m = from.recv();
                // System.out.println(m[0]);
                // System.out.println(new String(m));
                if (from.hasReceiveMore()) {
                    to.sendMore(m);
                } else {
                    to.send(m);
                    break;
                }
            }
        }
    }
}
