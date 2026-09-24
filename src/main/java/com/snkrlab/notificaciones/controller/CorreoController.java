package com.snkrlab.notificaciones.controller;

import com.snkrlab.notificaciones.service.CorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class CorreoController {

    @Autowired
    private CorreoService CorreoService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarCorreo(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {

        try {
            CorreoService.enviarCorreo(to, subject, body);

            return ResponseEntity.ok(
                    "Correo aceptado por el servidor para envío a " + to
            );
        } catch (MailException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Error al enviar el correo.");
        }
    }
}