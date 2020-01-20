package main;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import protos.negotiation.NegotiationReply;

import java.io.IOException;
import java.net.Socket;

public class Listener extends Thread {

    private CodedInputStream cis;

    public Listener(CodedInputStream cis) {
        this.cis = cis;
    }

    @Override
    public void run() {

        while (true) {

            try {
                int len = cis.readRawLittleEndian32();
                System.out.println("Message Length: " + len);
                NegotiationReply.BaseReply.ReplyType reply =
                        NegotiationReply.BaseReply.parseFrom(cis.readRawBytes(len)).getType();

                switch (reply) {
                    case SUCCESS:
                        System.out.println("RESULTADO ENCOMENDA: SUCESSO!");
                        break;

                    case CANCELATION:
                        System.out.println("RESULTADO ENCOMENDA: CANCELADA!");
                        break;

                    default:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
