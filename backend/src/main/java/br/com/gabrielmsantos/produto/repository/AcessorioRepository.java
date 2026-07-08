package br.com.gabrielmsantos.produto.repository;

import br.com.gabrielmsantos.produto.entity.Acessorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {
}
