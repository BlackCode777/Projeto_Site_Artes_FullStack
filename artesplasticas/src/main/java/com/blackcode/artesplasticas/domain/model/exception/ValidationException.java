package com.blackcode.artesplasticas.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de Domínio: Indica que uma regra de negócio foi violada (ex: username duplicado).
 * Mapeada para o status HTTP 400 (Bad Request) pela camada Web.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST) // Sugestão para o futuro tratamento em um ControllerAdvice
public class ValidationException extends RuntimeException{

    public ValidationException(String message){
        super(message);
    }
}
