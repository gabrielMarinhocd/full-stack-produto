package br.com.gabrielmsantos.produto.controller.dto;

import br.com.gabrielmsantos.produto.entity.Acessorio;
import br.com.gabrielmsantos.produto.entity.Notebook;

import java.util.ArrayList;
import java.util.List;

public record NotebookDto(
        Long id,
        String nome,
        String descricao,
        Double preco,
        List<AcessorioDto> acessorios
) {

    public NotebookDto(Notebook model) {
        this(
                model.getId(),
                model.getNome(),
                model.getDescricao(),
                model.getPreco(),
                model.getAcessorios() == null
                        ? new ArrayList<>()
                        : model.getAcessorios()
                        .stream()
                        .map(AcessorioDto::new)
                        .toList()
        );
    }


    public Notebook toModel() {

        Notebook model = new Notebook();

        model.setId(this.id);
        model.setNome(this.nome);
        model.setDescricao(this.descricao);
        model.setPreco(this.preco);

        if (this.acessorios != null) {

            List<Acessorio> lista =
                    this.acessorios.stream()
                            .map(AcessorioDto::toModel)
                            .collect(
                                    java.util.stream.Collectors.toCollection(ArrayList::new)
                            );

            model.setAcessorios(lista);
        } else {
            model.setAcessorios(new ArrayList<>());
        }

        return model;
    }
}