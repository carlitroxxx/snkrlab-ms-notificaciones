package com.snkrlab.notificaciones.controller;

import com.snkrlab.notificaciones.rabbitmq.CorreoDTO;
import com.snkrlab.notificaciones.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class CorreoController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarCorreo(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {

        CorreoDTO mensaje = new CorreoDTO(to, subject, body);

        try {
            rabbitTemplate.convertAndSend(
                    "",
                    RabbitMQConfig.COLA_CORREOS,
                    mensaje
            );

            return ResponseEntity.accepted()
                    .body("Solicitud publicada para procesar el correo.");
        } catch (AmqpException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("No se pudo publicar la solicitud en RabbitMQ.");
        }
    }
}