package com.blackcode.artesplasticas.infrastructure.adapter.out.messagingKafka;

import com.blackcode.artesplasticas.application.mapper.ArtistaPostgresMongoMapper_ApplicMapper;
import com.blackcode.artesplasticas.application.port.out.NotificarArtistaMessaging_ApplicationPortOut;
import com.blackcode.artesplasticas.domain.model.ArtistaMongo_DomainModel;
import com.blackcode.artesplasticas.domain.model.ArtistaPostgres_DomainModel;
import com.blackcode.artesplasticas.domain.model.eventsDtos.ArtistaKafkaProducerAdapter_DomainModelEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Adapter Kafka para notificação de eventos relacionados a Artista.
 * Implementa a interface de porta de saída para notificar sobre criação ou atualização de artistas.
 * <p>
 * Autor: Anderson Martins Desenvolvimento de Sistemas
 * Ano de Atuação: 2015 - 2025
 * Versão: 1.0.0
 * Data de Criação desta POC: 2025-11-30
 */
@Component
@ConditionalOnProperty(value = "spring.app.kafka.enabled", havingValue = "true")
public class ArtistaKafkaProducerAdapter_InfraAdapterOutMessag
        implements NotificarArtistaMessaging_ApplicationPortOut {

    private static final Logger logger = LoggerFactory.getLogger(ArtistaKafkaProducerAdapter_InfraAdapterOutMessag.class);
    private static final String TOPIC = "artesplasticas.artista.replicacao";
    private final ArtistaPostgresMongoMapper_ApplicMapper mapper;
    private final KafkaTemplate<String, ArtistaKafkaProducerAdapter_DomainModelEvents> kafkaTemplate;

    public ArtistaKafkaProducerAdapter_InfraAdapterOutMessag(
            KafkaTemplate<String, ArtistaKafkaProducerAdapter_DomainModelEvents> kafkaTemplate,
            ArtistaPostgresMongoMapper_ApplicMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    @Override
    public void notificarArtistaCriadoOuAtualizado(ArtistaPostgres_DomainModel artistaPostgres) {

        ArtistaMongo_DomainModel mongo = mapper.toMongo(artistaPostgres);

        String operacao = (artistaPostgres.getId() == null) ? "CRIACAO" : "ATUALIZACAO";

        ArtistaKafkaProducerAdapter_DomainModelEvents event =
                ArtistaKafkaProducerAdapter_DomainModelEvents.fromDomainModel(mongo, operacao);

        kafkaTemplate.send(TOPIC, String.valueOf(mongo.getIdArtistaPostgres()), event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        logger.info("Evento enviado. TOPIC={}, KEY={}", TOPIC, mongo.getIdArtistaPostgres());
                    } else {
                        logger.error("Erro ao enviar evento", ex);
                    }
                });
    }

    @Override
    public void notificarArtistaCriadoOuAtualizado(ArtistaMongo_DomainModel artistaMongo) {
        String operacao = (artistaMongo.getId() == null) ? "CRIACAO" : "ATUALIZACAO";

        ArtistaKafkaProducerAdapter_DomainModelEvents event =
                ArtistaKafkaProducerAdapter_DomainModelEvents.fromDomainModel(artistaMongo, operacao);

        kafkaTemplate.send(TOPIC, String.valueOf(artistaMongo.getIdArtistaPostgres()), event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        logger.info("Evento enviado. TOPIC={}, KEY={}", TOPIC, artistaMongo.getIdArtistaPostgres());
                    } else {
                        logger.error("Erro ao enviar evento", ex);
                    }
                });
    }
}
