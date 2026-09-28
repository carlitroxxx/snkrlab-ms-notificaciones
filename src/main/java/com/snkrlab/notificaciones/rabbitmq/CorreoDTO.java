package com.snkrlab.notificaciones.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorreoDTO {

    private String to;
    private String subject;
    private String body;
}