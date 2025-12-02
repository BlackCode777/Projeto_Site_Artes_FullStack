package com.blackcode.artesplasticas.infrastructure.adapter.in.web;
import com.blackcode.artesplasticas.infrastructure.adapter.in.web.dtos.ErrorResponseDto_InfraAdaptInWebDtos;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Manipulador Global de Exceções para a camada de Web (InfraAdaptInWeb).
 * Captura exceções lançadas pelos controladores REST e retorna respostas HTTP apropriadas.
 *
 * Autor: Anderson Martins Desenvolvimento de Sistemas
 * Ano de Atuação: 2015 - 2025
 * Versão: 1.0.0
 * Data de Criação desta POC: 2025-11-30
 */
@RestControllerAdvice
public class GlobalExceptionHandler_InfraAdaptInWeb
        extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<String> detalhes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toList();

        ErrorResponse error = ErrorResponse.builder(ex, status, "Erro de validação de argumentos.")
                .title("Erro de validação.")
                .detail("Detalhes: " + String.join("; ", detalhes))
                .build();

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, "Violação de integridade de dados.")
                .title("Violação de integridade de dados.")
                .detail("Detalhe: " + ex.getRootCause().getMessage())
                .build();
    }

    /**
     * Tratamento para 404 Not Found (Exceções de Recurso Não Encontrado)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto_InfraAdaptInWebDtos> handleResponseNotFoundException(
            ResourceNotFoundException ex,  WebRequest request){
        ErrorResponseDto_InfraAdaptInWebDtos errorResponse = ErrorResponseDto_InfraAdaptInWebDtos.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Recurso não encontrado.")
                .message("Ocorreu um erro 404 inesperado. Detalhe >>> " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Tratamento para 400 Bad Request (Exceções de Regra de Negócio/Validação)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto_InfraAdaptInWebDtos> handleValidationException(
            IllegalArgumentException ex, WebRequest request){

        ErrorResponseDto_InfraAdaptInWebDtos errorResponse = ErrorResponseDto_InfraAdaptInWebDtos.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro de validação de negócio.")
                .message("Ocorreu um erro 400 inesperado. Detalhe >>> " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Tratamento de Exceções Genéricas (500 Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto_InfraAdaptInWebDtos> handleGenericException(
            Exception ex, WebRequest request){

        ErrorResponseDto_InfraAdaptInWebDtos errorResponse = ErrorResponseDto_InfraAdaptInWebDtos.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Erro interno do servidor.")
                .message("Ocorreu um erro 500 inesperado. Detalhe >>> " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
