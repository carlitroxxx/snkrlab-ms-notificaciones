package com.snkrlab.notificaciones.rabbitmq;

import com.snkrlab.notificaciones.service.CorreoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorreoListener {

    @Autowired
    private CorreoService correoService;

    @RabbitListener(queues = RabbitMQConfig.COLA_CORREOS)
    public void recibirCorreo(CorreoDTO mensaje) {
        correoService.enviarCorreo(
                mensaje.getTo(),
                mensaje.getSubject(),
                mensaje.getBody()
        );
    }
}