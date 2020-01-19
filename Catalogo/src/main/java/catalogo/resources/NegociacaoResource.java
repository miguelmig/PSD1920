package catalogo.resources;

import catalogo.CatalogoApp;
import catalogo.representations.Fabricante;

import catalogo.representations.Negociacao;
import com.google.common.base.Optional;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/negociacao")
@Produces(MediaType.APPLICATION_JSON)
public class NegociacaoResource {
    private final CatalogoApp app;
    public NegociacaoResource(CatalogoApp app) {
        this.app = app;
    }

    @GET
    public List<Negociacao> getNegociacoes()
    {
        return app.getNegociacoes();
    }

    @POST
    @Path("/add/")
    public Negociacao add(@NotNull @Valid Negociacao negociacao)
    {
        app.addNegociacao(negociacao);
        return negociacao;
    }

    @PUT
    @Path("/update")
    public Response update(@NotNull @Valid Negociacao negociacao)
    {
        boolean updated = app.updateNegociacao(negociacao);
        if(updated)
            return Response.ok().build();

        return Response.notModified().build();
    }

    @DELETE
    @Path("/delete")
    public Response delete(@NotNull @Valid Negociacao negociacao)
    {
        boolean deleted = app.deleteNegociacao(negociacao);
        if(deleted)
            return Response.ok().build();

        return Response.notModified().build();
    }
}

