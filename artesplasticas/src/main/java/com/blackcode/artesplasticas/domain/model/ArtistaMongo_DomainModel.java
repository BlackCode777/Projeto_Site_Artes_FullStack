package com.blackcode.artesplasticas.domain.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa um Artista armazenado no MongoDB.
 * Read model otimizado para consulta (artista + obras).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "IND_ARTST")  // nome da coleção no Mongo
public class ArtistaMongo_DomainModel{

    private Long id;  // ID do documento Mongo (ObjectId em string)
    private Long idArtistaPostgres;  // opcional: referência ao ID do Postgres
    private String nomeCompleto;
    private String email;

    @Indexed(unique = true)
    private String username;
    private String biografia;
    private LocalDateTime dataRegistro;
    private List<ObraArteMongoDB_DomainModel> obrasDeArte;

}
