package com.blackcode.artesplasticas.infrastructure.adapter.out.cacheMongo;

import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório MongoDB para a entidade ArtistaMongo_DomainModel.
 * Fornece métodos para operações CRUD e consultas personalizadas.
 */
@Repository
public interface ArtistaMongoRepository_InfraAdapterOutCache
        extends MongoRepository<ArtistaMongo_DomainModel, Long> {

    /**
     * Consulta um Artista e suas obras pelo username (o link público).
     * Esta é a busca principal para montar o site do artista.
     * @param userName Username (slug) do artista.
     * @return Optional contendo o Artista completo e suas obras, se encontrado.
     */
    @Query("{ 'username': ?0 }")
    Optional<ArtistaMongo_DomainModel> buscarPortifolioPorUserNameMongoDB(String userName);
}
