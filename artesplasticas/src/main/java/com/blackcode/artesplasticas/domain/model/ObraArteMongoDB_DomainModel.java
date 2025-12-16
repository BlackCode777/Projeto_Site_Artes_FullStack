package com.blackcode.artesplasticas.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Subdocumento de obra de arte para o MongoDB.
 * Usado dentro de ArtistaMongo_DomainModel como lista de obras.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObraArteMongoDB_DomainModel{

    @Field("id_obra")
    private Long idObraArte;

    @Field("titulo")
    private String titulo;

    @Field("descricao")
    private String descricao;

    @Field("ano_criacao")
    private Integer anoCriacao;

    @Field("tecnica")
    private String tecnicaUtilizada;

    @Field("imagem_obra")
    private byte[] imagemObra;

}
