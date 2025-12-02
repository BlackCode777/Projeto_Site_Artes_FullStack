package com.blackcode.artesplasticas.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de Domínio: Indica que um recurso solicitado não foi encontrado.
 * Mapeada para o status HTTP 404 (Not Found) pela camada Web (ControllerAdvice, que será implementado futuramente).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException_DomainModel extends RuntimeException{

    public ResourceNotFoundException_DomainModel(String message){
        super(message);
    }
}
