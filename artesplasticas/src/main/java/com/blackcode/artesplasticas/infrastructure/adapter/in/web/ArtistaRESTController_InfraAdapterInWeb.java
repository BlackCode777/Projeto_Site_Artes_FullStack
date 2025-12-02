package com.blackcode.artesplasticas.infrastructure.adapter.in.web;

import com.blackcode.artesplasticas.application.port.in.GerenciarArtista_ApplicationPortIn;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.DTO.ArtistaPostgresDto_DomainModelDto;
import com.blackcode.artesplasticas.infrastructure.adapter.in.web.dtos.ArtistaResponse_InfraAdapInWebDtos;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gerenciar artistas.
 * Fornece endpoints para CRUD de artistas.
 *
 * Autor: Anderson Martins Desenvolvimento de Sistemas
 * Ano de Atuação: 2015 - 2025
 * Versão: 1.0.0
 * Data de Criação desta POC: 2025-11-30
 */
@RestController
@RequestMapping("/api/artistas")
public class ArtistaRESTController_InfraAdapterInWeb{

    // O Controller chama o Port de Entrada, não o Use Case diretamente.
    private final GerenciarArtista_ApplicationPortIn gerenciarArtistaPort;

    public ArtistaRESTController_InfraAdapterInWeb(
            GerenciarArtista_ApplicationPortIn gerenciarArtistaPort){
        this.gerenciarArtistaPort = gerenciarArtistaPort;
    }

    // Deletar um artista por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarArtistaPorId(@PathVariable Long id){
        gerenciarArtistaPort.deletarUmArtistaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaResponse_InfraAdapInWebDtos> editarArtistaPorId(
            @PathVariable Long id,
            @RequestBody ArtistaPostgresDto_DomainModelDto artistaDto){
        ArtistaPostgres_DomainModel artistaAtualizado = gerenciarArtistaPort.editarUmArtistaPorId(id, artistaDto);
        ArtistaResponse_InfraAdapInWebDtos artistaResponse =
                ArtistaResponse_InfraAdapInWebDtos.fromDomainModel(artistaAtualizado);
        return ResponseEntity.ok(artistaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaResponse_InfraAdapInWebDtos> buscarArtistaPorId(
            @PathVariable Long id){
        ArtistaPostgres_DomainModel artista = gerenciarArtistaPort.consultarPorId(id);

        ArtistaResponse_InfraAdapInWebDtos artistaResponse =
                ArtistaResponse_InfraAdapInWebDtos.fromDomainModel(artista);

        return ResponseEntity.ok(artistaResponse);
    }

    @GetMapping
    public ResponseEntity<List<ArtistaResponse_InfraAdapInWebDtos>> buscarTodosOsArtistas(){
        List<ArtistaPostgres_DomainModel> artistas = gerenciarArtistaPort.buscarTodosOsArtistas();

        List<ArtistaResponse_InfraAdapInWebDtos> artistaResponse = artistas.stream()
                .map(ArtistaResponse_InfraAdapInWebDtos::fromDomainModel)
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok(artistaResponse);
    }

    @PostMapping
    public ResponseEntity<ArtistaResponse_InfraAdapInWebDtos> cadastrarArtista(
            @RequestBody @Valid ArtistaPostgresDto_DomainModelDto artistaDto){

        // 'Chama o Use Case via Port de Entrada
        ArtistaPostgres_DomainModel artistaSalvo = gerenciarArtistaPort.cadastrar(artistaDto);

        // Mapeia o domain Model de volta para o DTO de resposta
        ArtistaResponse_InfraAdapInWebDtos artistaResponse =
                ArtistaResponse_InfraAdapInWebDtos.fromDomainModel( artistaSalvo );

        return ResponseEntity.status(HttpStatus.CREATED).body(artistaResponse);
    }

}
