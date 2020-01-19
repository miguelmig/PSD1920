package catalogo.resources;

import catalogo.representations.Fabricante;

import catalogo.representations.Negociacao;
import com.google.common.base.Optional;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/negociacao")
@Produces(MediaType.APPLICATION_JSON)
public class NegociacaoResource {
    private List<Negociacao> negociacoes;
    public NegociacaoResource(List<Negociacao> negociacoes) {
        this.negociacoes = negociacoes;
    }

    @GET
    public List<Negociacao> getNegociacoes()
    {
        List<Negociacao> negociacoes = new ArrayList<>(this.negociacoes);
        return negociacoes;
    }

    @POST
    @Path("/add/")
    public Negociacao add(@NotNull @Valid Negociacao negociacao)
    {
        this.negociacoes.add(negociacao);
        return negociacao;
    }
}

