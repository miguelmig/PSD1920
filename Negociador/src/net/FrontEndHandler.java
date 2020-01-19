package net;

import com.google.protobuf.Descriptors;
import io.dropwizard.validation.OneOf;
import org.eclipse.jetty.util.IO;
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


public class FrontEndHandler {
    private RESTClient rest;
    private int port;
    private ServerSocket sockserver;
    private List<Negociacao> negociacoes = new ArrayList<>();
    private Map<String, Fabricante> fabricantes = new HashMap<>();

    private Socket socket;
    private CodedInputStream cis;
    private CodedOutputStream cos;

    public FrontEndHandler(int port, RESTClient rest) throws Exception
    {
        this.port = port;
        this.rest = rest;
        this.startServer();
    }

    private void startServer() throws IOException {
        this.sockserver = new ServerSocket(port);
    }

    public void run() throws IOException
    {
        while(true)
        {
            negociacoes.clear();
            socket = sockserver.accept();
            cis = CodedInputStream.newInstance(socket.getInputStream());
            cos = CodedOutputStream.newInstance(socket.getOutputStream());
            System.out.println("[*] Front End Connected!");
            handleRequests();

            ScheduledThreadPoolExecutor e = new ScheduledThreadPoolExecutor(1);

            e.scheduleAtFixedRate(this::checkNegociacoesEmCurso, 1, 5, TimeUnit.SECONDS);
        }
    }

    private void handleRequests() throws IOException {
        int len = cis.readRawLittleEndian32();
        Message.GenericMessage msg = Message.GenericMessage.parseFrom(cis.readRawBytes(len));

        if(msg.hasEncomenda())
        {
            System.out.println("[+] Got Add Encomenda Message!");
            Message.AddEncomendaMessage encomenda = msg.getEncomenda();
            for(Negociacao n : negociacoes)
            {
                if(n.getFabricante().equals(encomenda.getManufacturer()) &&
                        n.getArtigo().getNome().equals(encomenda.getProduct()))
                {
                    OrdemCompra ordem = new OrdemCompra(encomenda.getImporterName(),
                            encomenda.getQuantity(),
                            encomenda.getWillingPrice());

                    System.out.println("[+] Updated a Negotiation with a new Order");
                    n.getOrdens().add(ordem);
                    updateNegociacao(n);
                }
            }
        }
        else if(msg.hasArtigo())
        {
            System.out.println("[+] Got Add Artigo Message!");
            Message.AddArtigoMessage artigo = msg.getArtigo();
            Negociacao n = criarNegociacao(artigo.getManufacturerName(), artigo.getProductName(),
                    artigo.getMinimumQuantity(), artigo.getMaximumQuantity(), artigo.getUnitaryPrice(),
                    artigo.getNegotiationTime());
            updateFabricante(n.getFabricante(), n.getArtigo());
        }
    }

    private synchronized Negociacao criarNegociacao(String fabricante_nome,
                                               String nome_artigo, int min_quantity, int max_quantity,
                                               int preco_unitario, int tempo_negociacao)
    {
        Artigo a = new Artigo(nome_artigo,
                min_quantity, max_quantity,
                preco_unitario,
                (int)(System.currentTimeMillis() / 1000) + tempo_negociacao );
        Negociacao n = new Negociacao(fabricante_nome, a, new ArrayList<>());
        negociacoes.add(n);
        return n;
    }

    private synchronized void updateNegociacao(Negociacao n)
    {
        rest.updateNegociacao(n);
    }

    private synchronized void updateFabricante(String nome_fabricante, Artigo a)
    {
        Fabricante fab = fabricantes.get(nome_fabricante);
        if(fab == null)
            fab = new Fabricante(nome_fabricante, new ArrayList<>());

        fab.getArtigos().add(a);
        fabricantes.put(nome_fabricante, fab);
        rest.updateFabricante(fab);
    }

    private void endNegociacao(Negociacao n, Iterator<Negociacao> i)
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
            //TODO: SEND NO DEAL MESSAGE TO FRONT END
        }
        else
        {
            OrdemCompra ordem = vencedora.get();
            //TODO: SEND WINNER MESSAGE TO FRONT END
        }
        i.remove();
    }

    private synchronized void checkNegociacoesEmCurso()
    {
        if(negociacoes.isEmpty())
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
                endNegociacao(n, i);
            }
        }

    }
}
