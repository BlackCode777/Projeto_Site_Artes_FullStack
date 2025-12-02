package com.blackcode.artesplasticas.domain.model.eventsDtos;

import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.ObraArteMongoDB_DomainModel;
import com.blackcode.artesplasticas.domain.model.ObraArtePostgres_DomainModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para eventos de Artista enviados via Kafka.
 * Contém informações relevantes sobre o artista e suas obras,
 * além do tipo de operação realizada (criação, atualização, deleção).
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
public class ArtistaKafkaProducerAdapter_DomainModelEvents{

    private Long id;
    private String nomeCompleto;
    private String email;
    private String username;
    private String biografia;
    private LocalDateTime dataRegistro;
    private String operacao; // CRIACAO, ATUALIZACAO, DELECAO
    private List<ObraArteMongoDB_DomainModel> obrasDeArte;

    public static ArtistaKafkaProducerAdapter_DomainModelEvents fromDomainModel(
            ArtistaMongo_DomainModel artista,
            String operacao) {

        return ArtistaKafkaProducerAdapter_DomainModelEvents.builder()
                .id(artista.getId())
                .nomeCompleto(artista.getNomeCompleto())
                .email(artista.getEmail())
                .username(artista.getUsername())
                .biografia(artista.getBiografia())
                .dataRegistro(artista.getDataRegistro())
                .operacao(operacao)
                .obrasDeArte(artista.getObrasDeArte())
                .build();
    }

}
