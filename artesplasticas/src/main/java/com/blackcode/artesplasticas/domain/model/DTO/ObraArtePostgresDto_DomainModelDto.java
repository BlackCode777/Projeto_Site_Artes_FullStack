package com.blackcode.artesplasticas.domain.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObraArtePostgresDto_DomainModelDto{

    private Long idObraArte;
    private String titulo;
    private String descricao;
    private Integer anoCriacao;
    private String tecnicaUtilizada;
    // Opcional: apenas o ID do artista, para evitar referência circular pesada
    private Long idArtista;

    private byte[] imagemObra;

}
