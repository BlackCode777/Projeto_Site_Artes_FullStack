package com.blackcode.artesplasticas.infrastructure.adapter.out.dbPostgres;

import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório JPA para a entidade Artista_DomainModel.
 * Fornece métodos para operações CRUD e consultas personalizadas.
 */
@Repository
public interface ArtistaJpaRepository_InfraAdapterOutDB
    extends JpaRepository<ArtistaPostgres_DomainModel, Long>{

    /**
     * Busca um Artista pelo seu nome de usuário.
     * @param username Nome de usuário do Artista a ser buscado.
     * @return Um Optional contendo o Artista, se encontrado, ou vazio se não encontrado.
     */
    Optional<ArtistaPostgres_DomainModel> findByUsername(String username);
}
