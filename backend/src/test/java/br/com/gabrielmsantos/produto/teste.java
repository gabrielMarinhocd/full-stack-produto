package br.com.gabrielmsantos.produto;

import br.com.gabrielmsantos.produto.entity.Notebook;
import br.com.gabrielmsantos.produto.repository.NotebookRepository;
import br.com.gabrielmsantos.produto.service.NotebookService;
import br.com.gabrielmsantos.produto.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotebookServiceTest {
    @Mock
    private NotebookRepository repository;

    @InjectMocks
    private NotebookService service;

    private Notebook notebook;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        notebook = new Notebook();
        notebook.setId(1L);
        notebook.setNome("Notebook Gamer");
        notebook.setDescricao("Notebook para jogos.");
        notebook.setPreco(5000.0);
        notebook.setAcessorios(new ArrayList<>());
    }

    @Test
    @DisplayName("Deve listar notebooks")
    void deveListarNotebooks() {
        when(repository.findAll())
                .thenReturn(List.of(notebook));

        List<Notebook> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals(
                "Notebook Gamer",
                resultado.get(0).getNome()
        );
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve listar notebooks com seus acessórios")
    void deveListarNotebooksComAcessorios() {
        when(repository.listarComAcessorios())
                .thenReturn(List.of(notebook));

        List<Notebook> resultado =
                service.listarComAcessorios();

        assertFalse(resultado.isEmpty());
        verify(repository)
                .listarComAcessorios();
    }

    @Test
    @DisplayName("Deve buscar notebooks por nome do acessório")
    void deveBuscarNotebookPorAcessorio() {
        when(repository.buscarPorNomeAcessorio("mouse"))
                .thenReturn(List.of(notebook));

        List<Notebook> resultado =
                service.buscarPorNomeAcessorio("mouse");

        assertEquals(1, resultado.size());
        verify(repository)
                .buscarPorNomeAcessorio("mouse");
    }

    @Test
    @DisplayName("Deve buscar notebook por id")
    void deveBuscarNotebookPorId() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(notebook));

        Notebook resultado =
                service.buscarPorId(1L);

        assertEquals(
                "Notebook Gamer",
                resultado.getNome()
        );
        verify(repository)
                .findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando notebook não existir")
    void deveLancarErroQuandoNotebookNaoExistir() {
        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> service.buscarPorId(1L)
        );

        verify(repository)
                .findById(1L);
    }

    @Test
    @DisplayName("Deve cadastrar notebook")
    void deveCadastrarNotebook() {
        when(repository.save(notebook))
                .thenReturn(notebook);

        Notebook resultado =
                service.cadastrar(notebook);

        assertNotNull(resultado);
        assertEquals(
                "Notebook Gamer",
                resultado.getNome()
        );

        verify(repository)
                .save(notebook);
    }


    @Test
    @DisplayName("Deve atualizar notebook")
    void deveAtualizarNotebook() {
        Notebook atualizado = new Notebook();
        atualizado.setId(1L);
        atualizado.setNome("Notebook Pro");
        atualizado.setDescricao("Novo");
        atualizado.setPreco(7000.0);
        atualizado.setAcessorios(new ArrayList<>());

        when(repository.findById(1L))
                .thenReturn(Optional.of(notebook));

        when(repository.save(any(Notebook.class)))
                .thenReturn(atualizado);

        Notebook resultado =
                service.atualizar(1L, atualizado);

        assertEquals(
                "Notebook Pro",
                resultado.getNome()
        );

        verify(repository)
                .save(any(Notebook.class));
    }

    @Test
    @DisplayName("Deve excluir notebook")
    void deveExcluirNotebook() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(notebook));

        service.excluir(1L);

        verify(repository)
                .delete(notebook);
    }
}