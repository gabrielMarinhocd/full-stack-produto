package br.com.gabrielmsantos.produto.service;

import br.com.gabrielmsantos.produto.entity.Notebook;
import br.com.gabrielmsantos.produto.repository.NotebookRepository;
import br.com.gabrielmsantos.produto.service.exception.BusinessException;
import br.com.gabrielmsantos.produto.service.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class NotebookService {
    private static final Logger log =
            LoggerFactory.getLogger(NotebookService.class);

    private final NotebookRepository notebookRepository;

    public NotebookService(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    @Transactional(readOnly = true)
    public List<Notebook> listar() {
        log.info("Listando todos os notebooks.");

        List<Notebook> notebooks =
                this.notebookRepository.findAll();

        log.info(
                "Total de notebooks encontrados: {}",
                notebooks.size()
        );
        return notebooks;
    }

    @Transactional(readOnly = true)
    public List<Notebook> listarComAcessorios() {
        log.info(
                "Listando notebooks com seus acessórios."
        );

        List<Notebook> notebooks =
                this.notebookRepository.listarComAcessorios();

        log.info(
                "Total de notebooks encontrados com acessórios: {}",
                notebooks.size()
        );

        return notebooks;
    }

    @Transactional(readOnly = true)
    public List<Notebook> buscarPorNomeAcessorio(
            String nomeAcessorio) {
        log.info(
                "Buscando notebooks pelo acessório: {}",
                nomeAcessorio
        );

        if (nomeAcessorio == null || nomeAcessorio.isBlank()) {
            log.warn(
                    "Tentativa de busca com nome de acessório vazio."
            );
            throw new BusinessException(
                    "Nome do acessório deve ser informado."
            );
        }

        List<Notebook> notebooks =
                this.notebookRepository
                        .buscarPorNomeAcessorio(nomeAcessorio);

        log.info(
                "Encontrados {} notebooks com o acessório {}.",
                notebooks.size(),
                nomeAcessorio
        );

        return notebooks;
    }

    @Transactional(readOnly = true)
    public Notebook buscarPorId(Long id) {
        log.info(
                "Buscando notebook por ID: {}",
                id
        );

        return this.notebookRepository
                .findById(id)
                .orElseThrow(() -> {

                    log.warn(
                            "Notebook não encontrado. ID: {}",
                            id
                    );

                    return new NotFoundException();
                });
    }

    @Transactional
    public Notebook cadastrar(Notebook notebook) {
        log.info(
                "Iniciando cadastro de notebook."
        );

        ofNullable(notebook)
                .orElseThrow(() -> {

                    log.warn(
                            "Tentativa de cadastrar notebook nulo."
                    );

                    return new BusinessException(
                            "Notebook para cadastro não pode ser nulo."
                    );
                });

        notebook.setId(null);

        Notebook notebookSalvo =
                this.notebookRepository.save(notebook);

        log.info(
                "Notebook cadastrado com sucesso. ID: {} Nome: {}",
                notebookSalvo.getId(),
                notebookSalvo.getNome()
        );

        return notebookSalvo;
    }

    @Transactional
    public Notebook atualizar(
            Long id,
            Notebook notebook) {

        log.info(
                "Atualizando notebook. ID: {}",
                id
        );

        ofNullable(notebook)
                .orElseThrow(() -> {
                    log.warn(
                            "Tentativa de atualizar notebook nulo."
                    );

                    return new BusinessException(
                            "Notebook para atualização não pode ser nulo."
                    );
                });

        Notebook notebookBanco =
                this.buscarPorId(id);

        if (notebook.getId() != null &&
                !notebookBanco.getId()
                        .equals(notebook.getId())) {

            log.warn(
                    "IDs divergentes na atualização. Banco: {} Enviado: {}",
                    notebookBanco.getId(),
                    notebook.getId()
            );

            throw new BusinessException(
                    "Os IDs do notebook devem ser iguais."
            );
        }

        notebookBanco.setNome(notebook.getNome());
        notebookBanco.setDescricao(notebook.getDescricao());
        notebookBanco.setPreco(notebook.getPreco());
        notebookBanco.setAcessorios(notebook.getAcessorios());

        Notebook notebookAtualizado =
                this.notebookRepository.save(notebookBanco);

        log.info(
                "Notebook atualizado com sucesso. ID: {}",
                notebookAtualizado.getId()
        );

        return notebookAtualizado;
    }

    @Transactional
    public void excluir(Long id) {

        log.info(
                "Excluindo notebook. ID: {}",
                id
        );

        Notebook notebookBanco =
                this.buscarPorId(id);

        this.notebookRepository.delete(notebookBanco);

        log.info(
                "Notebook excluído com sucesso. ID: {}",
                id
        );
    }
}