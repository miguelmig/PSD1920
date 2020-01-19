package net;

import org.eclipse.jetty.util.IO;
import rest.RESTClient;
import rest.representation.Negociacao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;


public class FrontEndHandler {
    private RESTClient rest;
    private int port;
    private ServerSocket sockserver;
    private List<Negociacao> negociacoes = new ArrayList<>();

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
        }
    }

    private void handleRequests() throws IOException {
        int len = cis.readRawLittleEndian32();
        

    }

    private void checkNegociacoesEmCurso()
    {

    }
}
