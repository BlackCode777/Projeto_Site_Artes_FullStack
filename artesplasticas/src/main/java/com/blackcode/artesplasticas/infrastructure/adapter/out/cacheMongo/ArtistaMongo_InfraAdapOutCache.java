package com.blackcode.artesplasticas.infrastructure.adapter.out.cacheMongo;

import com.blackcode.artesplasticas.application.port.out.BuscarArtistaConsultaMongoDb_ApplicPortOut;
import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import com.blackcode.artesplasticas.domain.model.ObraArteMongoDB_DomainModel;
import com.blackcode.artesplasticas.domain.model.ObraArtePostgres_DomainModel;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public abstract class ArtistaMongo_InfraAdapOutCache
        implements BuscarArtistaConsultaMongoDb_ApplicPortOut{

    private final ArtistaMongoRepository_InfraAdapterOutCache artistaRepository;

    public ArtistaMongo_InfraAdapOutCache(ArtistaMongoRepository_InfraAdapterOutCache artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    private ArtistaMongo_DomainModel toDomainModel(ArtistaMongo_DomainModel queryModel) {
        if (queryModel == null) return null;

        // Mapeamento das Obras (simplificado, já que ObraArte_DomainModel é reutilizada)
        List<ObraArteMongoDB_DomainModel> obras = queryModel.getObrasDeArte() != null
                ? queryModel.getObrasDeArte()
                : Collections.emptyList();

        // O Artista_DomainModel é recriado como um objeto de consulta
        return ArtistaMongo_DomainModel.builder()
                .id(queryModel.getId())
                .nomeCompleto(queryModel.getNomeCompleto())
                .username(queryModel.getUsername())
                .biografia(queryModel.getBiografia())
                .dataRegistro(queryModel.getDataRegistro())
                .obrasDeArte(obras)
                // Campos de escrita (email, senha) são intencionalmente ignorados nesta porta de leitura
                .build();
    }

    @Override
    public Optional<ArtistaMongo_DomainModel> buscarPortifolioPorUserNameMongoDB(String username) {
        return artistaRepository.buscarPortifolioPorUserNameMongoDB(username)
                .map(this::toDomainModel);
    }

    @Override
    public List<ArtistaMongo_DomainModel> buscarTodosParaConsulta() {
        return artistaRepository.findAll().stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ObraArtePostgres_DomainModel> buscarObraDeArtePorIdMongoDB(Long id) {
        // Para buscar uma obra individual no MongoDB, precisamos de um repositório de Obra específico,
        // ou realizar uma busca na lista de obras de todos os artistas (pouco eficiente).
        //
        // Assumindo que você terá uma coleção separada para ObraArte no MongoDB para buscas rápidas:
        // return obraArteMongoRepository.findById(id);

        // Se a busca for complexa, precisaremos do Repositório de Obra
        // Por enquanto, faremos um Optional vazio, pois o Repositório de Obra não foi definido.

        return Optional.empty();
    }
}
