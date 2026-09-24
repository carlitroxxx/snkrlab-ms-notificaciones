package com.snkrlab.notificaciones.repository;

import com.snkrlab.notificaciones.model.Correo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorreoRepository extends JpaRepository<Correo, Long> {
}