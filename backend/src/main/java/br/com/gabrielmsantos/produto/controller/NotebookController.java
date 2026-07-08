package br.com.gabrielmsantos.produto.controller;

import br.com.gabrielmsantos.produto.controller.dto.NotebookDto;
import br.com.gabrielmsantos.produto.service.NotebookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/notebooks")
@Tag(
        name = "Notebook Controller",
        description = "API REST para gerenciamento de notebooks e acessorios."
)
public record NotebookController(NotebookService notebookService) {
    @GetMapping
    @Operation(
            summary = "Listar notebooks",
            description = "Retorna todos os notebooks cadastrados."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operação realizada com sucesso."
    )
    public ResponseEntity<List<NotebookDto>> listar() {

        return ResponseEntity.ok(
                notebookService.listar()
                        .stream()
                        .map(NotebookDto::new)
                        .toList()
        );
    }

    @GetMapping("/acessorios")
    @Operation(
            summary = "Listar notebooks com acessórios",
            description = "Retorna todos os notebooks carregando seus acessórios."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Operação realizada com sucesso."
    )
    public ResponseEntity<List<NotebookDto>> listarComAcessorios() {

        return ResponseEntity.ok(
                notebookService.listarComAcessorios()
                        .stream()
                        .map(NotebookDto::new)
                        .toList()
        );
    }

    @GetMapping("/acessorios/buscar")
    @Operation(
            summary = "Buscar notebooks por acessório",
            description = "Busca notebooks que possuem um acessório pelo nome ou parte do nome."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operação realizada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Nome do acessório inválido."
            )
    })
    public ResponseEntity<List<NotebookDto>> buscarPorNomeAcessorio(
            @RequestParam String nome) {

        return ResponseEntity.ok(
                notebookService.buscarPorNomeAcessorio(nome)
                        .stream()
                        .map(NotebookDto::new)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar notebook por ID",
            description = "Retorna um notebook específico pelo identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Notebook encontrado."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Notebook não encontrado."
            )
    })
    public ResponseEntity<NotebookDto> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new NotebookDto(
                        notebookService.buscarPorId(id)
                )
        );
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar notebook",
            description = "Cria um notebook com seus acessórios."
    )
    public ResponseEntity<NotebookDto> cadastrar(
            @RequestBody NotebookDto dto) {

        var notebook =
                notebookService.cadastrar(dto.toModel());

        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(notebook.getId())
                        .toUri();

        return ResponseEntity
                .created(location)
                .body(new NotebookDto(notebook));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir notebook",
            description = "Remove um notebook pelo ID."
    )
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        notebookService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}