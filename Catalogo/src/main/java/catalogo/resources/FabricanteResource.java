package catalogo.resources;

import catalogo.representations.Fabricante;
import com.google.common.base.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/fabricante")
@Produces(MediaType.APPLICATION_JSON)
public class FabricanteResource {
    private List<Fabricante> fabricantes;

    public FabricanteResource(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }

    @GET
    public List<Fabricante> getFabricante(@QueryParam("name") Optional<String> name) {
        if(name.isPresent()) {
            for (Fabricante fab : fabricantes) {
                if (fab.getNome().equals(name))
                {
                    List<Fabricante> ret = new ArrayList<>();
                    ret.add(fab);
                    return ret;
                }
            }
            return new ArrayList<>();
        }
        else
        {
            return this.fabricantes;
        }
    }
    
    @PUT
    @Path("/")
    public Response put(@NotNull @Valid Fabricante input) {
        for(Fabricante fab : fabricantes)
        {
            if(fab.getNome().equals(input.getNome())) {
                fab.setArtigos(input.getArtigos());
                return Response.ok().build();
            }
        }
        return Response.notModified().build();
    }


    @POST
    @Path("/add/{nome}")
    public Response add(@PathParam("nome") String nome) {
        fabricantes.add(new Fabricante(nome, new ArrayList<>()));
        return Response.ok().build();
    }

}

