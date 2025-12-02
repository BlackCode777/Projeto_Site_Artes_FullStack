package com.blackcode.artesplasticas.application.mapper;

import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.DTO.ArtistaPostgresDto_DomainModelDto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = { ObraArtePostgresResponseMapper_ApplicMapper.class }
)
public abstract class ArtistaPostgresResponseMapper_ApplicMapper{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataRegistro", ignore = true)
    public abstract ArtistaPostgres_DomainModel toEntity(ArtistaPostgresDto_DomainModelDto dto);

    // Entity → DTO (com lista de obras)
    public abstract ArtistaPostgresDto_DomainModelDto toDto(ArtistaPostgres_DomainModel entity);

    // Depois que o MapStruct montar o artista, amarramos o "pai" nas obras
    @AfterMapping
    private static void vincularObras(@MappingTarget ArtistaPostgres_DomainModel artista) {
        if (artista.getObrasDeArte() != null) {
            artista.getObrasDeArte().forEach(obra -> obra.setArtista(artista));
        }
    }
}