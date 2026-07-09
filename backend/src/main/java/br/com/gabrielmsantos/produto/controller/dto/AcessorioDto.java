package br.com.gabrielmsantos.produto.controller.dto;

import br.com.gabrielmsantos.produto.entity.Acessorio;

public record AcessorioDto(
        Long codigo,
        String nome,
        String descricao,
        Double preco
) {

    public AcessorioDto(Acessorio model) {
        this(
                model.getCodigo(),
                model.getNome(),
                model.getDescricao(),
                model.getPreco()
        );
    }

    public Acessorio toModel() {
        Acessorio model = new Acessorio();

        model.setNome(this.nome);
        model.setDescricao(this.descricao);
        model.setPreco(this.preco);

        return model;
    }
}