package com.blackcode.artesplasticas.application.port.out;

import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.ObraArtePostgres_DomainModel;

import java.util.List;
import java.util.Optional;

public interface BuscarArtistaConsultaMongoDb_ApplicPortOut{

    /**
     * Consulta um Artista e suas obras pelo username (o link público).
     * Esta é a busca principal para montar o site do artista.
     *
     * @param userName Username (slug) do artista.
     * @return Optional contendo o Artista completo e suas obras, se encontrado.
     */
    Optional<ArtistaMongo_DomainModel> buscarPortifolioPorUserNameMongoDB(String userName);

    /**
     * Lista todos os Artistas disponíveis no cacheMongo de consulta.
     * @return Lista de Artistas (geralmente usada para páginas de galeria principal).
     */
    List<ArtistaPostgres_DomainModel> buscarTodosArtistaMongoDB();

    List<ArtistaMongo_DomainModel> buscarTodosParaConsulta();

    /**
     * Busca uma Obra de Arte pelo seu ID no MongoDB.
     * @param id ID da Obra de Arte a ser buscada.
     * @return Um Optional contendo a Obra de Arte, se encontrada, ou vazio se não encontrada.
     */
    Optional<ObraArtePostgres_DomainModel> buscarObraDeArtePorIdMongoDB(Long id);
}
