package com.blackcode.artes_plasticas.providers;

import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.DTO.ArtistaPostgresDto_DomainModelDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

public class Providers_TestesParaTodasAsClasses{

    // IDs de Teste (Mocked/Simulados)
    public static final Long ID_EXISTENTE = 10L;
    public static final Long ID_INEXISTENTE = 99L;
    public static final Long ID_CRIADO = 11L;

    // Usernames de Teste
    public static final String USERNAME_EXISTENTE = "micha_teste";
    public static final String USERNAME_NOVO = "tarsila_moderna";
    public static final String USERNAME_EDICAO = "monet_atualizado";

    // Dados de Login/Payload
    public static final String NOME_COMPLETO_NOVO = "Tarsila do Teste";
    public static final String EMAIL_NOVO = "tarsila@teste.com";
    public static final String SENHA_MOCK = "senha123";
    public static final String BIOGRAFIA_NOVA = "Biografia moderna de teste.";

    // Constantes de Infraestrutura para o Teste de Integração
    public static final String API_BASE = "/api/artistas";
    public static final String MOCK_AUTH_TOKEN = "MOCK_TOKEN_ADMIN";

    public static ArtistaPostgres_DomainModel criarArtistaPostgresDomainModel() {
        return ArtistaPostgres_DomainModel.builder()
                .id(ID_CRIADO)
                .nomeCompleto(NOME_COMPLETO_NOVO)
                .email(EMAIL_NOVO)
                .username(USERNAME_NOVO)
                .senha(SENHA_MOCK)
                .biografia(BIOGRAFIA_NOVA)
                .dataRegistro(LocalDateTime.now())
                .build();
    }

    public static ArtistaMongo_DomainModel criarArtistaMongoDomainModel() {
        return ArtistaMongo_DomainModel.builder()
                .id(ID_CRIADO)
                .nomeCompleto(NOME_COMPLETO_NOVO)
                .username(USERNAME_NOVO)
                .biografia(BIOGRAFIA_NOVA)
                .dataRegistro(LocalDateTime.now())
                .build();
    }

    // DTO de comando para cadastro de Artista (request body)
    public static ArtistaPostgresDto_DomainModelDto criarComandoCadastroArtista() {
        return ArtistaPostgresDto_DomainModelDto.builder()
                .nomeCompleto(NOME_COMPLETO_NOVO)
                .email(EMAIL_NOVO)
                .username(USERNAME_NOVO)
                .senha(SENHA_MOCK)
                .biografia(BIOGRAFIA_NOVA)
                // .obrasDeArte(...) se quiser mockar obras aqui
                .build();
    }

    // Serializa o DTO de comando para JSON (para usar com WebTestClient ou MockMvc)
    public static String comandoArtistaToJson(ArtistaPostgresDto_DomainModelDto command) {
        try {
            return new ObjectMapper().writeValueAsString(command);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Falha ao serializar DTO para JSON: " + e.getMessage(), e);
        }
    }
}
