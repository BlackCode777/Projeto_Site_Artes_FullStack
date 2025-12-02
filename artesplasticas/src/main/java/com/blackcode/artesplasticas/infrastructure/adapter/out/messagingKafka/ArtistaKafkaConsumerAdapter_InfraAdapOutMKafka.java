package com.blackcode.artesplasticas.infrastructure.adapter.out.messagingKafka;

import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import com.blackcode.artesplasticas.domain.model.eventsDtos.ArtistaKafkaProducerAdapter_DomainModelEvents;
import com.blackcode.artesplasticas.infrastructure.adapter.out.cacheMongo.ArtistaMongoRepository_InfraAdapterOutCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Adapter Kafka para consumo de eventos relacionados a Artista.
 * Atualiza o modelo de consulta MongoDB com base nos eventos recebidos.
 *
 * Autor: Anderson Martins Desenvolvimento de Sistemas
 * Ano de Atuação: 2015 - 2025
 * Versão: 1.0.0
 * Data de Criação desta POC: 2025-12-01
 */
@Component
public class ArtistaKafkaConsumerAdapter_InfraAdapOutMKafka{

    private static final Logger log =
            LoggerFactory.getLogger(ArtistaKafkaConsumerAdapter_InfraAdapOutMKafka.class);

    private final ObjectMapper objectMapper;
    private final ArtistaMongoRepository_InfraAdapterOutCache artistaMongoRepository;

    public ArtistaKafkaConsumerAdapter_InfraAdapOutMKafka(
            ObjectMapper objectMapper,
            ArtistaMongoRepository_InfraAdapterOutCache artistaMongoRepository) {
        this.objectMapper = objectMapper;
        this.artistaMongoRepository = artistaMongoRepository;
    }

    @KafkaListener(
            topics = "artesplasticas.artista.replicacao",
            groupId = "artesplasticas-artista-consumer-group", // "artesplasticas-artista-consumer-group-dev-test"
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumirEventoArtistaReplicacao(@Payload String payload) {
        try {
            ArtistaKafkaProducerAdapter_DomainModelEvents evento =
                    objectMapper.readValue(payload, ArtistaKafkaProducerAdapter_DomainModelEvents.class);

            log.info("Evento recebido do Kafka: {}", evento);

            ArtistaMongo_DomainModel doc = mapEventToQueryModel(evento);

            switch (evento.getOperacao()) {
                case "CRIACAO", "ATUALIZACAO" -> {
                    artistaMongoRepository.save(doc);
                    log.info("Artista replicado/atualizado no Mongo: id={}", doc.getId());
                }
                case "DELECAO" -> {
                    artistaMongoRepository.deleteById(doc.getId());
                    log.info("Artista removido do Mongo: id={}", doc.getId());
                }
                default -> log.warn("Operação desconhecida: {}", evento.getOperacao());
            }

        } catch (Exception e) {
            log.error("Erro ao processar mensagem Kafka: {}", payload, e);
        }
    }

    private ArtistaMongo_DomainModel mapEventToQueryModel(
            ArtistaKafkaProducerAdapter_DomainModelEvents event) {

        return ArtistaMongo_DomainModel.builder()
                .id(event.getId())
                .nomeCompleto(event.getNomeCompleto())
                .username(event.getUsername())
                .email(event.getEmail())
                .biografia(event.getBiografia())
                .dataRegistro(event.getDataRegistro())
                .obrasDeArte(event.getObrasDeArte())
                .build();
    }

}
