package catalogo;

import catalogo.representations.Fabricante;
import catalogo.representations.Negociacao;
import catalogo.resources.FabricanteResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import catalogo.resources.NegociacaoResource;
import catalogo.health.TemplateHealthCheck;

import java.util.ArrayList;
import java.util.List;

public class CatalogoApp extends Application<CatalogoConfiguration> {
    public List<Negociacao> negociacoes_em_curso = new ArrayList<>();
    public List<Fabricante> fabricantes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        new CatalogoApp().run(args);
    }

    @Override
    public String getName() { return "Catalogo"; }

    @Override
    public void initialize(Bootstrap<CatalogoConfiguration> bootstrap) {

    }

    @Override
    public void run(CatalogoConfiguration configuration,
                    Environment environment) {
        environment.jersey().register(
            new NegociacaoResource(this.negociacoes_em_curso));
        environment.jersey().register(
            new FabricanteResource(this.fabricantes)
        );

    }

}

