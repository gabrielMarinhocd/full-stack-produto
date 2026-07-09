package br.com.gabrielmsantos.produto.repository;

import br.com.gabrielmsantos.produto.entity.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

    @Query("""
       SELECT DISTINCT n
       FROM Notebook n
       LEFT JOIN FETCH n.acessorios
       """)
    List<Notebook> listarComAcessorios();

    @Query("""
    SELECT DISTINCT n
    FROM Notebook n
    JOIN FETCH n.acessorios a
    WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAcessorio, '%'))
    """)
    List<Notebook> buscarPorNomeAcessorio(
            @Param("nomeAcessorio") String nomeAcessorio);
}
