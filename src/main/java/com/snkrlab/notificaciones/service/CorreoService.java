package com.snkrlab.notificaciones.service;

import com.snkrlab.notificaciones.model.Correo;
import com.snkrlab.notificaciones.repository.CorreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CorreoService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CorreoRepository correoRepository;

    @Value("${spring.mail.username}")
    private String remitente;

    public void enviarCorreo(String to, String subject, String body) {

        Correo correo = new Correo();
        correo.setDestinatario(to);
        correo.setAsunto(subject);
        correo.setMensaje(body);
        correo.setFecha(LocalDateTime.now());
        correo.setEstado("PENDIENTE");

        correo = correoRepository.save(correo);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remitente);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            correo.setEstado("ERROR");
            correoRepository.save(correo);

            throw e;
        }

        correo.setEstado("ENVIADO");
        correoRepository.save(correo);
    }
}