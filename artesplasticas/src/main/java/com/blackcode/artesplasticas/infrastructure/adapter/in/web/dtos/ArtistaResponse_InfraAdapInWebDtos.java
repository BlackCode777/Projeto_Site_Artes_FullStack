package com.blackcode.artesplasticas.infrastructure.adapter.in.web.dtos;

import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistaResponse_InfraAdapInWebDtos{

    private Long id;
    private String nomeCompleto;
    private String email;
    private String username;
    private String biografia;
    private LocalDateTime dataRegistro;
    private int totalObras; // Exibe a contagem, em vez da lista completa (para evitar loops e sobrecarga inicial)

    // Método estático para mapear o Domain Model para o DTO
    public static ArtistaResponse_InfraAdapInWebDtos fromDomainModel(
           ArtistaPostgres_DomainModel artistaDomainModel){
        return ArtistaResponse_InfraAdapInWebDtos.builder()
                .id(artistaDomainModel.getId())
                .nomeCompleto(artistaDomainModel.getNomeCompleto())
                .email(artistaDomainModel.getEmail())
                .username(artistaDomainModel.getUsername())
                .biografia(artistaDomainModel.getBiografia())
                .dataRegistro(artistaDomainModel.getDataRegistro())
                .totalObras(
                        artistaDomainModel.getObrasDeArte() != null ?
                                artistaDomainModel.getObrasDeArte().size() : 0)
                .build();
    }

    // Método estático para mapear o DTO para o Domain Model
    public static ArtistaPostgres_DomainModel toDomainModel(
            ArtistaResponse_InfraAdapInWebDtos artistaResponseDto){
        return ArtistaPostgres_DomainModel.builder()
                .id(artistaResponseDto.getId())
                .nomeCompleto(artistaResponseDto.getNomeCompleto())
                .email(artistaResponseDto.getEmail())
                .username(artistaResponseDto.getUsername())
                .biografia(artistaResponseDto.getBiografia())
                .dataRegistro(artistaResponseDto.getDataRegistro())
                // Note: totalObras is not mapped back as it's derived data
                .build();
    }
}
