package com.blackcode.artesplasticas.application.port.out;

import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;

/**
 * Port de Saída (Driving Port): Define as operações necessárias para enviar mensagens
 * (eventos) para um sistema de mensageria (Kafka).
 * O Use Case chama esta interface após operações transacionais críticas.
 *
 * Autor: Anderson Martins Desenvolvimento de Sistemas
 * Ano de Atuação: 2015 - 2025
 * Versão: 1.0.0
 * Data de Criação desta POC: 2025-11-30
 */
public interface NotificarArtistaMessaging_ApplicationPortOut{

    void notificarArtistaCriadoOuAtualizado(ArtistaMongo_DomainModel artista);

    /**
     * Envia uma notificação de criação ou atualização do Artista.
     * Esta notificação será usada pelo consumidor para replicar/atualizar
     * os dados no MongoDB (cacheMongo de consulta).
     * * @param artista O Artista_DomainModel que foi alterado.
     */
    void notificarArtistaCriadoOuAtualizado(ArtistaPostgres_DomainModel artista );
}
