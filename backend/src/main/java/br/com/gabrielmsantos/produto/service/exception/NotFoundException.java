package br.com.gabrielmsantos.produto.service.exception;

public class NotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("Recurso nao encontrado");
    }

}
