package com.ezpark.web_service.shared.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventListener {
    private final ObjectMapper objectMapper;
    private final Map<String, Consumer<String>> handlers = new ConcurrentHashMap<>();

    public void registerHandler(String topic, Consumer<String> handler) {
        handlers.put(topic, handler);
    }

    @KafkaListener(topics = {
            "vehicles.validation.response",
            "parkings.validation.response",
            "profiles.validation.response"
    }, groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        try {
            Map<String, Object> messageMap = objectMapper.readValue(message, Map.class);
            String topic = (String) messageMap.get("topic");

            if (handlers.containsKey(topic)) {
                handlers.get(topic).accept(message);
                log.info("Procesado mensaje de topic {}", topic);
            } else {
                log.warn("No hay handler registrado para el topic {}", topic);
            }
        } catch (IOException e) {
            log.error("Error procesando mensaje: {}", e.getMessage());
        }
    }
}
