package br.com.gabrielmsantos.produto.service;

import br.com.gabrielmsantos.produto.entity.Notebook;
import br.com.gabrielmsantos.produto.repository.NotebookRepository;
import jakarta.persistence.EntityNotFoundException;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
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
        notebook.setDescricao("Notebok para jogos.");
        notebook.setPreco(5000.0);
        notebook.setAcessorios(new ArrayList<>());
    }

    @Test
    void devewListarNotebooks() {
        when(repository.findAll())
                .thenReturn(List.of(notebook));

        List<Notebook> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals("Notebook Gamer", resultado.get(0).getNome());

        verify(repository, times(1)).findAll();
    }

    @Test
    @Description("Verifica a busca de todos os notebooks com os acessorios dele")
    void deveListarNotebooksComAcessorios() {
        when(repository.listarComAcessorios())
                .thenReturn(List.of(notebook));

        List<Notebook> resultado = service.listarComAcessorios();

        assertFalse(resultado.isEmpty());

        verify(repository)
                .listarComAcessorios();
    }

    @Test
    @Description("Verifica o metodo de busca de notebook que possuem um determinado acessorio")
    void deveBuscarNotebookPorAcessorio() {
        when(repository.buscarPorNomeAcessorio("mouse"))
                .thenReturn(List.of(notebook));

        List<Notebook> resultado =
                service.buscarPorNomeAcessorio("mouse");

        assertEquals(1, resultado.size());

        verify(repository).buscarPorNomeAcessorio("mouse");
    }

    @Test
    void deveBuscarNotebookPorId() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(notebook));

        Notebook resultado =
                service.buscarPorId(1L);

        assertEquals("Notebook Gamer", resultado.getNome());

        verify(repository).findById(1L);
    }

    @Test
    void deveLancarErroQuandoNotebookNaoExistir() {
        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> service.buscarPorId(1L)
        );

        verify(repository).findById(1L);
    }

    @Test
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

        verify(repository).save(notebook);
    }

    @Test
    void deveAtualizarNotebook() {
        Notebook atualizado = new Notebook();
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

        verify(repository).save(any(Notebook.class));
    }

    @Test
    void deveExcluirNotebook() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(notebook));

        service.excluir(1L);

        verify(repository).deleteById(1L);
    }
}