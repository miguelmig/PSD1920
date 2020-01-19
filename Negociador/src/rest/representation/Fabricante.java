package rest.representation;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Fabricante {
    @NotNull
    private String nome;

    private List<Artigo> artigos;

    public Fabricante(String nome, List<Artigo> artigos) {
        this.nome = nome;
        this.artigos = new ArrayList<>(artigos);
    }

    @JsonProperty
    public String getNome()
    {
        return this.nome;
    }

    @JsonProperty
    public List<Artigo> getArtigos() {
        return artigos;
    }

    @JsonProperty
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty
    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }
}

