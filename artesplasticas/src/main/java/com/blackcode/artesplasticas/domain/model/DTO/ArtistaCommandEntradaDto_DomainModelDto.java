package com.blackcode.artesplasticas.domain.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistaCommandEntradaDto_DomainModelDto{

    private String nomeCompleto;
    private String email;
    private String username;
    private String senhaCriptografada;        // <- senha vindo da API
    private String biografia;

}
