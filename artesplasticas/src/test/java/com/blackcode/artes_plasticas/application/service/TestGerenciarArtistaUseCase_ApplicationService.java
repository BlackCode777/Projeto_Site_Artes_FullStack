package com.blackcode.artes_plasticas.application.service;

import com.blackcode.artes_plasticas.providers.Providers_TestesParaTodasAsClasses;
import com.blackcode.artesplasticas.ArtesPlasticasApplication;
import com.blackcode.artesplasticas.application.port.out.NotificarArtistaMessaging_ApplicationPortOut;
import com.blackcode.artesplasticas.application.service.GerenciarArtistaUseCase_ApplicationService;
import com.blackcode.artesplasticas.domain.model.DTO.ArtistaPostgresDto_DomainModelDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Teste de Integração (Black-Box) para ArtistaRESTController.
 * Valida o fluxo completo: Web -> Controller -> Use Case -> JPA Adapter -> PostgreSQL.
 */
@SpringBootTest(classes = ArtesPlasticasApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
        scripts = "classpath:sql/dataPostgres.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class TestGerenciarArtistaUseCase_ApplicationService {

    @LocalServerPort
    private int port;

    private NotificarArtistaMessaging_ApplicationPortOut notificarArtistaPortOut;

    private WebTestClient testClient;

    @BeforeEach
    void setUp() {
        this.testClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + port)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("SistemaOrigem", "JUnit")
                .build();
    }

    private WebTestClient testClientWithToken(String token) {
        return testClient.mutate()
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }

    @Nested
    class OperacoesDeEscrita {

        @Test
        void testCadastrarArtista_Sucesso() {
            ArtistaPostgresDto_DomainModelDto novoArtistaJson =
                    Providers_TestesParaTodasAsClasses.criarComandoCadastroArtista();

            testClientWithToken(Providers_TestesParaTodasAsClasses.MOCK_AUTH_TOKEN)
                    .post().uri(Providers_TestesParaTodasAsClasses.API_BASE)
                    .bodyValue(novoArtistaJson)
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody()
                    .jsonPath("$.nomeCompleto").isEqualTo(Providers_TestesParaTodasAsClasses.NOME_COMPLETO_NOVO)
                    .jsonPath("$.username").isEqualTo(Providers_TestesParaTodasAsClasses.USERNAME_NOVO)
                    .jsonPath("$.id").exists();
                    //.jsonPath("$.totalObras").isEqualTo(0);
        }
    }

    @Test
    void testCadastrarArtista_PayloadInvalido_DeveRetornarBadRequest() {
        // DTO vazio / com campos obrigatórios nulos
        ArtistaPostgresDto_DomainModelDto dtoInvalido = new ArtistaPostgresDto_DomainModelDto();

        testClientWithToken(Providers_TestesParaTodasAsClasses.MOCK_AUTH_TOKEN)
                .post().uri(Providers_TestesParaTodasAsClasses.API_BASE)
                .bodyValue(dtoInvalido)
                .exchange()
                .expectStatus().isBadRequest(); // Esperado: 400 por violação de Bean Validation
    }

}
