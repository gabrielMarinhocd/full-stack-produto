package br.com.gabrielmsantos.produto.service;

import br.com.gabrielmsantos.produto.entity.Notebook;
import br.com.gabrielmsantos.produto.repository.NotebookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotebookService {

    private final NotebookRepository repository;

    public NotebookService(NotebookRepository repository) {
        this.repository = repository;
    }

    public List<Notebook> listar() {
        return repository.findAll();
    }

    public List<Notebook> listarComAcessorios() {
        return repository.listarComAcessorios();
    }

    public List<Notebook> buscarPorNomeAcessorio(String nomeAcessorio) {
        return repository.buscarPorNomeAcessorio(nomeAcessorio);
    }

    public Notebook buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Notebook não encontrado."));
    }

    public Notebook cadastrar(Notebook notebook) {
        notebook.setId(null); // garante criação
        return repository.save(notebook);
    }

    public Notebook atualizar(Long id, Notebook notebook) {
        Notebook existente = buscarPorId(id);

        existente.setNome(notebook.getNome());
        existente.setDescricao(notebook.getDescricao());
        existente.setPreco(notebook.getPreco());
        existente.setAcessorios(notebook.getAcessorios());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}