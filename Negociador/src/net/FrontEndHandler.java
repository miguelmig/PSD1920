package net;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.protobuf.Descriptors;
import io.dropwizard.validation.OneOf;
import org.eclipse.jetty.util.IO;
import protos.negotiation.NegotiationReply;
import rest.RESTClient;
import rest.representation.Artigo;
import rest.representation.Fabricante;
import rest.representation.Negociacao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import protos.message.*;
import rest.representation.OrdemCompra;

class FrontEndThread implements Runnable
{
    private CodedInputStream cis;
    private CodedOutputStream cos;
    private FrontEndHandler handler;
    private String username;

    public FrontEndThread(CodedInputStream cis, CodedOutputStream cos, FrontEndHandler handler) throws Exception
    {
        this.cis = cis;
        this.cos = cos;
        this.handler = handler;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                handleRequests();
            }
            catch(Exception e)
            {
                System.err.println(e.getMessage());
            }
        }
    }

    private void handleRequests() throws Exception {
        int len = cis.readRawLittleEndian32();

        System.out.println("[*] Message Length: " + len);
        Message.GenericMessage msg = Message.GenericMessage.parseFrom(cis.readRawBytes(len));

        System.out.println("[*] Got Message Type: " + msg.getType().toString());
        if(msg.hasAuthRequest())
        {
            Message.AuthenticationRequest request = msg.getAuthRequest();
            this.username = request.getUsername();
            handler.addFabricante(username);
            handler.registerUser(username, this);
            System.out.println("[+] Got a Authentication Message, User: " + username);
        }
        else if(msg.hasEncomenda())
        {
            System.out.println("[+] Got Add Encomenda Message!");
            Message.AddEncomendaMessage encomenda = msg.getEncomenda();
            for(Negociacao n : handler.getNegociacoes())
            {
                if(n.getNome_fabricante().equals(encomenda.getManufacturer()) &&
                        n.getArtigo().getNome().equals(encomenda.getProduct()))
                {
                    OrdemCompra ordem = new OrdemCompra(encomenda.getImporterName(),
                            encomenda.getQuantity(),
                            encomenda.getWillingPrice());

                    System.out.println("[+] Updated a Negotiation with a new Order");
                    n.getOrdens().add(ordem);
                    handler.updateNegociacao(n);
                }
            }
        }
        else if(msg.hasArtigo())
        {
            System.out.println("[+] Got Add Artigo Message!");
            Message.AddArtigoMessage artigo = msg.getArtigo();
            Negociacao n = handler.criarNegociacao(artigo.getManufacturerName(), artigo.getProductName(),
                    artigo.getMinimumQuantity(), artigo.getMaximumQuantity(), artigo.getUnitaryPrice(),
                    artigo.getNegotiationTime());
            handler.updateFabricante(n.getNome_fabricante(), n.getArtigo());
        }

    }
}


public class FrontEndHandler {
    private RESTClient rest;
    private int port;
    private ServerSocket sockserver;
    private List<Negociacao> negociacoes = new ArrayList<>();
    private Map<String, Fabricante> fabricantes = new HashMap<>();

    private List<CodedOutputStream> output_streams = new ArrayList<>();
    private ScheduledThreadPoolExecutor e;

    private Map<String, FrontEndThread> users = new HashMap<>();

    public FrontEndHandler(int port, RESTClient rest) throws Exception
    {
        this.port = port;
        this.rest = rest;
        this.startServer();
    }

    private void startServer() throws IOException {
        this.sockserver = new ServerSocket(port);
    }

    public void run() throws Exception
    {
        e = new ScheduledThreadPoolExecutor(1);
        e.scheduleAtFixedRate(this::checkNegociacoesEmCurso, 15, 5, TimeUnit.SECONDS);
        negociacoes.clear();
        while(true)
        {
            Socket socket = sockserver.accept();
            System.out.println("[*] Front End Connected for a new client!");
            CodedInputStream cis = CodedInputStream.newInstance(socket.getInputStream());
            CodedOutputStream cos = CodedOutputStream.newInstance(socket.getOutputStream());
            output_streams.add(cos);
            new Thread(
                    new FrontEndThread(
                            cis, cos, this)).start();
        }

    }

    public synchronized Negociacao criarNegociacao(String fabricante_nome,
                                               String nome_artigo, int min_quantity, int max_quantity,
                                               int preco_unitario, int tempo_negociacao) throws Exception
    {
        Artigo a = new Artigo(nome_artigo,
                min_quantity, max_quantity,
                preco_unitario,
                (int)(System.currentTimeMillis() / 1000) + tempo_negociacao );
        Negociacao n = new Negociacao(fabricante_nome, a, new ArrayList<>());
        //System.out.println("[*] New negotiation added! Artigo: " + a);
        /*
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(n);
        System.out.println(json);
        */

        negociacoes.add(n);
        rest.addNegociacao(n);
        return n;
    }

    public synchronized void updateNegociacao(Negociacao n) throws Exception
    {
        System.out.println("[*] Updating negotiation");

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(n);
        System.out.println(json);

        rest.updateNegociacao(n);
    }

    public synchronized void updateFabricante(String nome_fabricante, Artigo a) throws Exception
    {
        Fabricante fab = fabricantes.get(nome_fabricante);
        if(fab == null)
            fab = new Fabricante(nome_fabricante, new ArrayList<>());

        fab.getArtigos().add(a);
        fabricantes.put(nome_fabricante, fab);
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(fab);
        System.out.println(json);
        rest.updateFabricante(fab);
    }

    private void endNegociacao(Negociacao n, Iterator<Negociacao> i) throws Exception
    {
        int quantidade_minima = n.getArtigo().getQuantidade_minima();
        List<OrdemCompra> ordens = n.getOrdens();
        Optional<OrdemCompra> vencedora = ordens.stream()
                .filter(o -> o.getQuantidade() > quantidade_minima)
                .min((o1, o2) -> Integer.compare(o2.getPrecoUnitario() * o2.getQuantidade(),
                        o1.getPrecoUnitario() * o1.getQuantidade()));
        if(!vencedora.isPresent())
        {
            System.out.println("[!] Negotiation closed without enough quantity orders");
            sendAllReply(NegotiationReply.BaseReply.ReplyType.CANCELATION);
        }
        else
        {
            OrdemCompra ordem = vencedora.get();
            System.out.println("[!] Negotiation closed with a winner from: " + ordem.getNome_Importador());
            sendAllReply(NegotiationReply.BaseReply.ReplyType.SUCCESS);
        }
        rest.deleteNegociacao(n);
        Fabricante fab = fabricantes.get(n.getNome_fabricante());
        if(fab != null)
        {
            fab.getArtigos().removeIf(a -> a.equals(n.getArtigo()));
            rest.updateFabricante(fab);
        }
        i.remove();
    }

    public void sendAllReply(NegotiationReply.BaseReply.ReplyType type) throws Exception
    {
        protos.negotiation.NegotiationReply.BaseReply.Builder reply = protos.negotiation.NegotiationReply.BaseReply.newBuilder();
        reply.setType(NegotiationReply.BaseReply.ReplyType.SUCCESS);
        NegotiationReply.BaseReply reply_builded = reply.build();
        for(CodedOutputStream cos : output_streams) {
            reply_builded.writeTo(cos);
            cos.flush();
        }
    }
    public synchronized  List<Negociacao> getNegociacoes()
    {
        return this.negociacoes;
    }

    public synchronized void addFabricante(String nome)
    {
        rest.addFabricante(nome);
    }

    private synchronized void checkNegociacoesEmCurso()
    {
        if(negociacoes.size() == 0)
            return;

        System.out.println("[*] Checking current negotiations");
        int currentSeconds = (int)(System.currentTimeMillis() / 1000);
        Iterator<Negociacao> i = negociacoes.iterator();
        while (i.hasNext())
        {
            Negociacao n = i.next();
            int ending_time = n.getArtigo().getTempo_de_negociacao();
            if(ending_time <= currentSeconds)
            {
                System.out.println("[*] Ending negotiation!");
                try {
                    endNegociacao(n, i);
                }
                catch(Exception e) {

                }
            }
        }
    }

    public void registerUser(String username, FrontEndThread thread)
    {
        users.put(username, thread);
    }
}
