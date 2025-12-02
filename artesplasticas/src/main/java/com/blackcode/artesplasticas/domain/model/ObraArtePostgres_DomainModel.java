package com.blackcode.artesplasticas.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@Table(name = "TB_OBR_ART")
@AllArgsConstructor
@NoArgsConstructor
@ToString( exclude = "artista" )
@EqualsAndHashCode(of = {"idObraArte", "titulo"})
public class ObraArtePostgres_DomainModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObraArte;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private Integer anoCriacao;

    @Column(nullable = false, length = 100)
    private String tecnicaUtilizada;

    //@ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista_id", nullable = false)
    private ArtistaPostgres_DomainModel artista;

}
