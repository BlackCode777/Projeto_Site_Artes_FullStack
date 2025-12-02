package com.blackcode.artesplasticas.domain.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArtistaPostgresDto_DomainModelDto{

    @NotBlank(message = "Nome completo é obrigatório.")
    private String nomeCompleto;
    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;
    private String biografia;
    @NotBlank(message = "Username é obrigatório.")
    private String username;
    @NotBlank(message = "Senha é obrigatória.")
    private String senha;
    private List<ObraArtePostgresDto_DomainModelDto> obrasDeArte;

}
