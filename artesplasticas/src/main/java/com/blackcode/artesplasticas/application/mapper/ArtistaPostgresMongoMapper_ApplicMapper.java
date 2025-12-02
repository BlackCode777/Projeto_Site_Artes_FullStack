package com.blackcode.artesplasticas.application.mapper;

import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.DTO.ArtistaPostgresDto_DomainModelDto;
import com.blackcode.artesplasticas.domain.model.ObraArteMongoDB_DomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = ObraArteMongoDB_DomainModel.class )
public interface ArtistaPostgresMongoMapper_ApplicMapper{

    // Postgres -> Mongo (incluindo o ID do Postgres)
    @Mapping(target = "idArtistaPostgres", source = "id")
    ArtistaMongo_DomainModel toMongo(ArtistaPostgres_DomainModel postgres);

    // DTO -> Postgres (id e dataRegistro definidos no use case)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataRegistro", ignore = true)
    ArtistaPostgres_DomainModel toPostgres(ArtistaPostgresDto_DomainModelDto dto);

    /*@Mapping(target = "id", ignore = true)                 // não vamos reaproveitar o ID do Mongo
    @Mapping(target = "senhaCriptografada", source = "senhaCriptografada", ignore = true) // senha não vem do Mongo
    @Mapping(target = "dataRegistro", source = "dataRegistro", ignore = true)
    @Mapping(target = "obrasDeArte", ignore = true)
    @Mapping(target = "id", ignore = true)
    ArtistaPostgres_DomainModel toPostgresFromMongo(ArtistaMongo_DomainModel mongo);

    @Mapping(target = "id", ignore = false)
    @Mapping(target = "id", source = "id")
    ArtistaMongo_DomainModel toMongoWithObras(ArtistaPostgres_DomainModel postgres);

    @Mapping(target = "idObraArte", ignore = true)         // será gerado no Postgres
    @Mapping(target = "artista", ignore = true)
    ObraArtePostgres_DomainModel toPostgresObra(ObraArteMongoDB_DomainModel mongoObra);

    @Mapping(target = "idObraArte", source = "idObraArte", ignore = true)
    ObraArteMongoDB_DomainModel toMongoObra(ObraArtePostgres_DomainModel postgresObra);*/


}
