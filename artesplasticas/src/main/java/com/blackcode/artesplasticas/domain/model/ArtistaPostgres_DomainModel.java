package com.blackcode.artesplasticas.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de domínio para representar um Artista armazenado em um banco de dados PostgreSQL.
 * Contém informações pessoais e uma lista de obras de arte associadas ao artista.
 *
 * * Autor: Anderson Martins Desenvolvimento de Sistemas
 *  * Ano de Atuação: 2015 - 2025
 *  * Versão: 1.0.0
 *  * Data de Criação desta POC: 2025-11-30
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_ARTST")
@EqualsAndHashCode(of = {"id", "email", "username"})
@ToString(exclude = "obrasDeArte") // Adicionado o exclude
public class ArtistaPostgres_DomainModel{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=150)
    private String nomeCompleto;

    @Column(nullable=false, length=150, unique=true)
    private String email;

    @Column(nullable = false, length = 100, unique=true)
    private String username;

    @Column(nullable = false, length = 255)
    private String senha; // Senha limpa (será criptografada no Use Case)

    @Column(columnDefinition="TEXT")
    private String biografia;

    @Column(nullable=false)
    private LocalDateTime dataRegistro;

    @OneToMany(
            mappedBy = "artista",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ObraArtePostgres_DomainModel> obrasDeArte;

}
