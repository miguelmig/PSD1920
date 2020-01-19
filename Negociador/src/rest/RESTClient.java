package rest;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import config.Config;
import rest.representation.Fabricante;
import rest.representation.Negociacao;

public class RESTClient {

    private Invocation.Builder invocationBuilder;
    private Client client;
    WebTarget webTarget;
    Invocation.Builder builder;
    public static final String host = "http://localhost:" + Config.CATALOGO_PORT;

    public RESTClient()
    {
        client = ClientBuilder.newClient();
    }

    private Invocation.Builder createInvocationBuilder(String path)
    {
        webTarget = client.target(host).path(path);
        builder = webTarget.request(MediaType.APPLICATION_JSON);
        return builder;
    }

    public void addNegociacao(Negociacao negociacao)
    {
        Invocation.Builder invocationBuilder = createInvocationBuilder("negociacao/add");
        Response rs = invocationBuilder.post(Entity.json(negociacao));
    }

    public void updateNegociacao(Negociacao negociacao)
    {
        Invocation.Builder invocationBuilder = createInvocationBuilder("negociacao/update");
        Response rs = invocationBuilder.put(Entity.json(negociacao));
    }

    public void deleteNegociacao(Negociacao negociacao) {
        Invocation.Builder invocationBuilder = createInvocationBuilder("negociacao/delete/" + negociacao.getNome_fabricante() + "/" + negociacao.getArtigo().getNome());
        Response rs = invocationBuilder.delete();
    }

    public void updateFabricante(Fabricante fabricante)
    {
        Invocation.Builder invocationBuilder = createInvocationBuilder("fabricante/update");
        Response rs = invocationBuilder.put(Entity.json(fabricante));
    }
}
