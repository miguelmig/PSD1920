package rest.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class OrdemCompra
{
    @NotNull
    @JsonProperty
    private String nome_importador;
    @JsonProperty
    private int quantidade;
    @JsonProperty
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
    public String getNome_importador() {
        return this.nome_importador;
    }

    @JsonProperty
    public int getQuantidade() {
        return this.quantidade;
    }

    @JsonProperty
    public int getPreco_unitario() {
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