package com.blackcode.artesplasticas.application.mapper;

import com.blackcode.artesplasticas.domain.model.DTO.ObraArtePostgresDto_DomainModelDto;
import com.blackcode.artesplasticas.domain.model.ObraArtePostgres_DomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ObraArtePostgresResponseMapper_ApplicMapper{

    // DTO -> entidade
    @Mapping(target = "artista", ignore = true)
    ObraArtePostgres_DomainModel toEntity(ObraArtePostgresDto_DomainModelDto dto);

    // entidade -> DTO (se quiser usar também)
    @Mapping(target = "idArtista", source = "artista.id")
    ObraArtePostgresDto_DomainModelDto toDto(ObraArtePostgres_DomainModel entity);
}