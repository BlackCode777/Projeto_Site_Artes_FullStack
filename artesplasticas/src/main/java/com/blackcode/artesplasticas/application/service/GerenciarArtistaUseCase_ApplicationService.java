package com.blackcode.artesplasticas.application.service;

import com.blackcode.artesplasticas.application.mapper.ArtistaPostgresResponseMapper_ApplicMapper;
import com.blackcode.artesplasticas.application.port.in.GerenciarArtista_ApplicationPortIn;
import com.blackcode.artesplasticas.application.port.out.GerenciarArtistaPersistence_ApplicationPortOut;
import com.blackcode.artesplasticas.application.port.out.NotificarArtistaMessaging_ApplicationPortOut;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.DTO.ArtistaPostgresDto_DomainModelDto;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Use Case (Serviço de Aplicação): Implementa a lógica de negócio definida no Port de Entrada.
 * Orquestra a execução, chamando os Ports de Saída (Persistência, Mensageria, etc.).
 *
 * Indica que esta classe é um serviço de aplicação (Use Case)
 *
 * Autor: Anderson Martins Desenvolvimento de Sistemas
 * Ano de Atuação: 2015 - 2025
 * Versão: 1.0.0
 * Data de Criação desta POC: 2025-11-30
 */
@Service
public class GerenciarArtistaUseCase_ApplicationService
        implements GerenciarArtista_ApplicationPortIn{

    private final GerenciarArtistaPersistence_ApplicationPortOut persistenciaPortOut;
    private final NotificarArtistaMessaging_ApplicationPortOut notificaArtistaPortOut;
    private final ArtistaPostgresResponseMapper_ApplicMapper artistaPostgresMapper;

    public GerenciarArtistaUseCase_ApplicationService(
            GerenciarArtistaPersistence_ApplicationPortOut persistenciaPortOut,
            NotificarArtistaMessaging_ApplicationPortOut notificaArtistaPortOut,
            ArtistaPostgresResponseMapper_ApplicMapper artistaPostgresMapper){
        this.artistaPostgresMapper = artistaPostgresMapper;
        this.persistenciaPortOut = persistenciaPortOut;
        this.notificaArtistaPortOut = notificaArtistaPortOut;
    }

    @Override
    public ArtistaPostgres_DomainModel cadastrar(ArtistaPostgresDto_DomainModelDto artistaGerenciado) {

        if (persistenciaPortOut.buscarArtistaPorUserName(artistaGerenciado.getUsername()).isPresent()) {
            throw new ValidationException(
                    "Username '" + artistaGerenciado.getUsername() + "' já está em uso.");
        }

        ArtistaPostgres_DomainModel novoArtista =
                artistaPostgresMapper.toEntity(artistaGerenciado);

        novoArtista.setDataRegistro(LocalDateTime.now());

        ArtistaPostgres_DomainModel artistaSalvo =
                persistenciaPortOut.salvarArtista(novoArtista);

        notificaArtistaPortOut.notificarArtistaCriadoOuAtualizado(artistaSalvo);

        return artistaSalvo;
    }

    @Override
    public ArtistaPostgres_DomainModel consultarPorId(Long id) {
        return persistenciaPortOut.buscarArtistaPorId(id)
                .orElseThrow(() ->
                        new ValidationException("Artista com ID " + id + " não encontrado."));
    }

    @Override
    public List<ArtistaPostgres_DomainModel> buscarTodosOsArtistas() {
        return persistenciaPortOut.buscarTodosOsArtistas();
    }

    @Override
    public ArtistaPostgres_DomainModel editarUmArtistaPorId(
            Long id,
            ArtistaPostgresDto_DomainModelDto artistaGerenciado) {

        ArtistaPostgres_DomainModel artistaExistente = consultarPorId(id);

        if (!artistaExistente.getUsername().equals(artistaGerenciado.getUsername()) &&
                persistenciaPortOut.buscarArtistaPorUserName(artistaGerenciado.getUsername()).isPresent()) {
            throw new ValidationException(
                    "Username '" + artistaGerenciado.getUsername() + "' já está em uso.");
        }

        artistaExistente.setNomeCompleto(artistaGerenciado.getNomeCompleto());
        artistaExistente.setEmail(artistaGerenciado.getEmail());
        artistaExistente.setUsername(artistaGerenciado.getUsername());
        artistaExistente.setBiografia(artistaGerenciado.getBiografia());

        ArtistaPostgres_DomainModel artistaAtualizado =
                persistenciaPortOut.atualizarArtista(artistaExistente);

        notificaArtistaPortOut.notificarArtistaCriadoOuAtualizado(artistaAtualizado);

        return artistaAtualizado;
    }

    @Override
    public void deletarUmArtistaPorId(Long id) {
        ArtistaPostgres_DomainModel artistaExistente = consultarPorId(id);

        if (persistenciaPortOut.buscarArtistaPorId(id).isEmpty()) {
            throw new ValidationException("Artista com ID " + id + " não encontrado.");
        }

        persistenciaPortOut.deletarArtistaPorId(id);
        notificaArtistaPortOut.notificarArtistaCriadoOuAtualizado(artistaExistente);
    }
}
