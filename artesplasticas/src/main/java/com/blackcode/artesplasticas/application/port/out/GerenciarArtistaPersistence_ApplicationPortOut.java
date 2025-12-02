package com.blackcode.artesplasticas.application.port.out;

import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;

import java.util.List;
import java.util.Optional;

public interface GerenciarArtistaPersistence_ApplicationPortOut{

    /**
     * Salva um Artista no sistema.
     * @param artista O Artista a ser salvo.
     * @return O Artista salvo, incluindo ID e data de registro.
     */
    ArtistaPostgres_DomainModel salvarArtista(ArtistaPostgres_DomainModel artista);

    /**
     * Atualiza um Artista existente no sistema.
     * @param idArtista O Artista com os dados atualizados.
     * @return O Artista atualizado.
     */
    ArtistaPostgres_DomainModel atualizarArtista(ArtistaPostgres_DomainModel idArtista);

    /**
     * Busca um Artista pelo seu ID.
     * @param id ID do Artista a ser buscado.
     * @return Um Optional contendo o Artista, se encontrado, ou vazio se não encontrado.
     */
    Optional<ArtistaPostgres_DomainModel> buscarArtistaPorId(Long id);

    /**
     * Busca um Artista pelo seu nome.
     * @param nome do Artista a ser buscado.
     */
    Optional<ArtistaPostgres_DomainModel> buscarArtistaPorUserName(String nome);

    /**
     * Lista todos os Artistas cadastrados no sistema.
     * @return Uma lista de todos os Artistas.
     */
    List<ArtistaPostgres_DomainModel> buscarTodosOsArtistas();

    /**
     * Deleta um Artista pelo seu ID.
     * @param id ID do Artista a ser deletado.
     */
    void deletarArtistaPorId(Long id);


}
