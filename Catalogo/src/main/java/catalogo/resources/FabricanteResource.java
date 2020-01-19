package catalogo.resources;

import catalogo.CatalogoApp;
import catalogo.representations.Fabricante;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

@Path("/fabricante")
@Produces(MediaType.APPLICATION_JSON)
public class FabricanteResource {
    private CatalogoApp app;

    public FabricanteResource(CatalogoApp app) {
        this.app = app;
    }

    @GET
    public List<Fabricante> getFabricante(@QueryParam("name") Optional<String> name) {
        return app.getFabricante(name);
    }

    @PUT
    @Path("/")
    public Response put(@NotNull @Valid Fabricante input) {
        boolean changed = this.app.updateFabricante(input);
        if(changed)
        {
            return Response.ok().build();
        }
        return Response.notModified().build();
    }


    @POST
    @Path("/add/{nome}")
    public Response add(@PathParam("nome") @NotNull String nome) {
        app.addFabricante(nome);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{nome}")
    public Response delete(@PathParam("nome") @NotNull String nome)
    {
        boolean deleted = app.deleteFabricante(nome);
        if(deleted)
            return Response.ok().build();
        return Response.notModified().build();
    }



}

