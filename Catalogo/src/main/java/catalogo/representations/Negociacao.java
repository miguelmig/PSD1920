package catalogo.representations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class OrdemCompra
{
    @NotNull
    private String nome_importador;
    private int quantidade;
    private int preco_unitario;

    @JsonCreator
    public OrdemCompra(@JsonProperty("nome_importador") String id_cliente,
                       @JsonProperty("quantidade") int quantidade,
                       @JsonProperty("preco_unitario") int preco_unitario)
    {
        this.nome_importador = id_cliente;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    @JsonProperty
    public String getImportador() {
        return this.nome_importador;
    }

    @JsonProperty
    public int getQuantidade() {
        return this.quantidade;
    }

    @JsonProperty
    public int getPrecoUnitario() {
        return this.preco_unitario;
    }

    @JsonProperty
    public void setNome_importador(String nome_importador) {
        this.nome_importador = nome_importador;
    }

    @JsonProperty
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @JsonProperty
    public void setPreco_unitario(int preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}

public class Negociacao {
    @NotNull
    private String nome_fabricante;

    @NotNull
    private Artigo artigo;

    private List<OrdemCompra> ordens;

    @JsonCreator
    public Negociacao(@JsonProperty("nome_fabricante") String nome_fabricante,
                      @JsonProperty("artigo") Artigo artigo,
                      @JsonProperty("ordens") List<OrdemCompra> ordens) {
        this.nome_fabricante = nome_fabricante;
        this.artigo = artigo;
        this.ordens = ordens;
    }

    @JsonProperty
    public String getNome_fabricante() {
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

