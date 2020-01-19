package rest.representation;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;

public class Artigo {
    @NotNull
    private final String nome;
    private final int quantidade_minima;
    private final int quantidade_maxima;
    private final int preco_minimo_unitario;
    private final int tempo_de_negociacao;

    public Artigo(String nome, int quantidade_minima, int quantidade_maxima, int preco_minimo_unitario, int tempo_de_negociacao) {
        this.nome = nome;
        this.quantidade_minima = quantidade_minima;
        this.quantidade_maxima = quantidade_maxima;
        this.preco_minimo_unitario = preco_minimo_unitario;
        this.tempo_de_negociacao = tempo_de_negociacao;
    }

    @JsonProperty
    public String getNome() {
        return nome;
    }

    @JsonProperty
    public int getQuantidade_minima() {
        return quantidade_minima;
    }

    @JsonProperty
    public int getQuantidade_maxima() {
        return quantidade_maxima;
    }

    @JsonProperty
    public int getPreco_minimo_unitario() {
        return preco_minimo_unitario;
    }

    @JsonProperty
    public int getTempo_de_negociacao() {
        return tempo_de_negociacao;
    }

    public boolean equals(Artigo other) {
        return (this.nome.equals(other.getNome()) &&
                this.quantidade_minima == other.getQuantidade_minima() &&
                this.quantidade_maxima == other.getQuantidade_maxima() &&
                this.preco_minimo_unitario == other.getPreco_minimo_unitario() &&
                this.tempo_de_negociacao == other.getTempo_de_negociacao());
    }


}
