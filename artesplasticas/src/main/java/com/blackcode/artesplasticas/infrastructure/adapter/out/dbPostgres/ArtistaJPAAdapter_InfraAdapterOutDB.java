package com.blackcode.artesplasticas.infrastructure.adapter.out.dbPostgres;

import com.blackcode.artesplasticas.application.port.out.GerenciarArtistaPersistence_ApplicationPortOut;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Adapter JPA para persistência de Artista_DomainModel.
 * Implementa a interface de porta de saída para gerenciar artistas.
 */
@Component
public class ArtistaJPAAdapter_InfraAdapterOutDB
        implements GerenciarArtistaPersistence_ApplicationPortOut{

    private final ArtistaJpaRepository_InfraAdapterOutDB artistaJpaRepository;

    public ArtistaJPAAdapter_InfraAdapterOutDB(ArtistaJpaRepository_InfraAdapterOutDB artistaJpaRepository){
        this.artistaJpaRepository = artistaJpaRepository;
    }

    /**
     * Salva um Artista no sistema.
     * @param artista O Artista a ser salvo.
     * @return O Artista salvo, incluindo ID e data de registro.
     */
    @Override
    public ArtistaPostgres_DomainModel salvarArtista(ArtistaPostgres_DomainModel artista){
        return artistaJpaRepository.save(artista);
    }

    /**
     * Busca um Artista pelo seu ID.
     * @param id ID do Artista a ser buscado.
     * @return Um Optional contendo o Artista, se encontrado, ou vazio se não encontrado.
     */
    @Override
    public Optional<ArtistaPostgres_DomainModel> buscarArtistaPorId(Long id){
        return artistaJpaRepository.findById(id);
    }

    /**
     * Lista todos os Artistas cadastrados no sistema.
     * @return Uma lista de todos os Artistas.
     */
    @Override
    public List<ArtistaPostgres_DomainModel> buscarTodosOsArtistas(){
        return artistaJpaRepository.findAll();
    }

    /**
     * Busca um Artista pelo seu nome.
     * @param nome do Artista a ser buscado.
     */
    @Override
    public Optional<ArtistaPostgres_DomainModel> buscarArtistaPorUserName(String nome){
        return artistaJpaRepository.findByUsername(nome);
    }

    /**
     * Deleta um Artista pelo seu ID.
     * @param id ID do Artista a ser deletado.
     */
    @Override
    public void deletarArtistaPorId(Long id){
        artistaJpaRepository.deleteAllById(List.of(id));
    }

    /**
     * Atualiza um Artista existente no sistema.
     * @param idArtista O Artista com os dados atualizados.
     * @return O Artista atualizado.
     */
    @Override
    public ArtistaPostgres_DomainModel atualizarArtista(ArtistaPostgres_DomainModel idArtista){
        return artistaJpaRepository.save(idArtista);
    }
}
