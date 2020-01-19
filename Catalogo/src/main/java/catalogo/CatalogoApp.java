package catalogo;

import catalogo.representations.Fabricante;
import catalogo.representations.Negociacao;
import catalogo.resources.FabricanteResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import catalogo.resources.NegociacaoResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogoApp extends Application<CatalogoConfiguration> {
    public final List<Negociacao> negociacoes_em_curso = new ArrayList<>();
    public final List<Fabricante> fabricantes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        new CatalogoApp().run(args);
    }

    @Override
    public String getName() { return "Catalogo"; }

    @Override
    public void initialize(Bootstrap<CatalogoConfiguration> bootstrap) {

    }

    public boolean updateFabricante(Fabricante input)
    {
        synchronized (this.fabricantes)
        {
            for(Fabricante fab : fabricantes)
            {
                if(fab.getNome().equals(input.getNome())) {
                    fab.setArtigos(input.getArtigos());
                    return true;
                }
            }
            return false;
        }
    }

    public List<Fabricante> getFabricante(String nome)
    {
        synchronized (this.fabricantes)
        {
            if(nome != null) {
                for (Fabricante fab : fabricantes) {
                    if (fab.getNome().equals(nome))
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
    }

    public void addFabricante(String nome)
    {
        synchronized (this.fabricantes)
        {
            fabricantes.add(new Fabricante(nome, new ArrayList<>()));
        }
    }

    public boolean deleteFabricante(String nome)
    {
        synchronized (this.fabricantes)
        {
            return fabricantes.removeIf(Fab -> { return Fab.getNome().equals(nome); });
        }
    }

    public List<Negociacao> getNegociacoes()
    {
        synchronized (this.negociacoes_em_curso)
        {
            return new ArrayList<>(this.negociacoes_em_curso);
        }
    }

    public void addNegociacao(Negociacao input)
    {
        synchronized (this.negociacoes_em_curso)
        {
            this.negociacoes_em_curso.add(input);
        }
    }

    public boolean updateNegociacao(Negociacao negociacao) {
        synchronized (this.negociacoes_em_curso)
        {
            Optional<Negociacao> found = this.negociacoes_em_curso.stream().filter(o -> o.getFabricante().equals(negociacao.getFabricante()) &&
                    o.getArtigo().equals(negociacao.getArtigo())).findFirst();
            if(!found.isPresent())
            {
                return false;
            }

            Negociacao result = found.get();
            result.setArtigo(negociacao.getArtigo());
            result.setOrdens(negociacao.getOrdens());
            return true;
        }
    }

    public boolean deleteNegociacao(String nome_fab, String nome_artigo)
    {
        synchronized (this.negociacoes_em_curso)
        {
			return negociacoes_em_curso.removeIf(n -> { 
				return n.getFabricante().equals(nome_fab) && n.getArtigo().getNome().equals(nome_artigo);
			});
        }
    }

    @Override
    public void run(CatalogoConfiguration configuration,
                    Environment environment) {
        environment.jersey().register(
            new NegociacaoResource(this));
        environment.jersey().register(
            new FabricanteResource(this));

    }

}

