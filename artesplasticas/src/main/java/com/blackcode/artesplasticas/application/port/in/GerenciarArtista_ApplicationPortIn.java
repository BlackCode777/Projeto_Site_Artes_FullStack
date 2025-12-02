package com.blackcode.artesplasticas.application.port.in;

import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.DTO.ArtistaPostgresDto_DomainModelDto;

import java.util.List;

/**
 * Port de Entrada (Driven Port): Define as operações disponíveis para a camada de infraestrutura (Web Adapters).
 * Responsável por gerenciar o ciclo de vida do Artista.
 */
public interface GerenciarArtista_ApplicationPortIn{

    /**
     * Cadastra um novo Artista no sistema.
     * @param command Dados de criação do Artista (contém todos os campos, exceto ID/datas).
     * @return O Artista completo, incluindo ID e data de registro.
     */
    ArtistaPostgres_DomainModel cadastrar(ArtistaPostgresDto_DomainModelDto command);

    /**
     * Consulta um Artista pelo seu ID.
     * @param id ID do Artista a ser consultado.
     * @return O Artista correspondente ao ID fornecido.
     */
    ArtistaPostgres_DomainModel consultarPorId(Long id);

    /**
     * Lista todos os Artistas cadastrados no sistema.
     * @return Lista de todos os Artistas.
     */
    List<ArtistaPostgres_DomainModel> buscarTodosOsArtistas();

    /**
     * Edita os dados de um Artista existente pelo seu ID.
     * @param id ID do Artista a ser editado.
     * @param command Dados atualizados do Artista (contém todos os campos, exceto ID/datas).
     * @return O Artista atualizado.
     */
    ArtistaPostgres_DomainModel editarUmArtistaPorId(Long id,ArtistaPostgresDto_DomainModelDto command);

    /**
     * Deleta um Artista pelo seu ID.
     * @param id ID do Artista a ser deletado.
     */
    void deletarUmArtistaPorId(Long id);

}
