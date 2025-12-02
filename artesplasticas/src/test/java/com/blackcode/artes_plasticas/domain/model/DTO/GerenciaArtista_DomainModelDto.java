package com.blackcode.artes_plasticas.domain.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GerenciaArtista_DomainModelDto{

    private String nomeCompleto;
    private String email;
    private String username;
    private String senhaCriptografada;
    private String biografia;

}
