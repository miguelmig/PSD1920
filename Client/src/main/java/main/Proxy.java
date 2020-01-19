package main;

import org.zeromq.ZMQ;

public class Proxy {

    private ZMQ.Poller items;
    private ZMQ.Socket pubs, subs;

    public Proxy(ZMQ.Context context, ZMQ.Socket pubs, ZMQ.Socket subs) {

        this.pubs = pubs;
        this.subs = subs;

        this.items = context.poller(2);
        this.items.register(pubs, ZMQ.Poller.POLLIN);
        this.items.register(subs, ZMQ.Poller.POLLIN);
    }

    public void poll() {

        System.out.println("proxy running");
        while (!Thread.currentThread().isInterrupted()) {
            items.poll();

            if(items.pollin(0)) {
                ZMQ.Socket from, to;
                if (items.pollin(0)) {
                    from = pubs; to = subs;
                    System.out.println("From pubs");
                } else {
                    from = subs; to = pubs;
                    System.out.println("From subs");
                }
                while (true) {
                    byte[] m = from.recv();
                    System.out.println(m[0]);
                    System.out.println(new String(m));
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
}
