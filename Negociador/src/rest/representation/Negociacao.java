package rest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Negociacao {
    @NotNull
    private String nome_fabricante;

    @NotNull
    private Artigo artigo;

    private List<OrdemCompra> ordens;

    public Negociacao(String nome_fabricante, Artigo artigo, List<OrdemCompra> ordens) {
        this.nome_fabricante = nome_fabricante;
        this.artigo = artigo;
        this.ordens = ordens;
    }

    @JsonProperty
    public String getFabricante() {
        return this.nome_fabricante;
    }

    @JsonProperty
    public Artigo getArtigo() {
        return this.artigo;
    }

    @JsonProperty
    public List<OrdemCompra> getOrdens() {
        return this.ordens;
    }

    @JsonProperty
    public void setNome_fabricante(String nome_fabricante) {
        this.nome_fabricante = nome_fabricante;
    }

    @JsonProperty
    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

    @JsonProperty
    public void setOrdens(List<OrdemCompra> ordens) {
        this.ordens = ordens;
    }
}

