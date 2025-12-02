package com.blackcode.artesplasticas.infrastructure.adapter.in.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para representar respostas de erro na API.
 * Contém informações detalhadas sobre o erro ocorrido.
 * Utilizado para padronizar as respostas de erro enviadas aos clientes.
 *
 * Autor: Anderson Martins Desenvolvimento de Sistemas
 * Ano de Atuação: 2015 - 2025
 * Versão: 1.0.0
 * Data de Criação desta POC: 2025-11-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto_InfraAdaptInWebDtos{

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
