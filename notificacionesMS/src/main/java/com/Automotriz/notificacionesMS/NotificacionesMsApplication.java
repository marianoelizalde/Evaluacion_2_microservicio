package com.Automotriz.notificacionesMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificacionesMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificacionesMsApplication.class, args);
    }
}

/**
 *
 *  GET    :8087/api/v1/notificaciones                      → lista todas
 *  GET    :8087/api/v1/notificaciones/{id}                 → busca por ID
 *  GET    :8087/api/v1/notificaciones/reserva/{reservaId}  → notificaciones de esa reserva
 *  GET    :8087/api/v1/notificaciones/tipo/{tipo}          → notificaciones por tipo
 *  POST   :8087/api/v1/notificaciones                      → crea
 *         body: { tipo: "CONFIRMACION", reservaId: 1, mensaje: "Su reserva fue confirmada" }
 *  DELETE :8087/api/v1/notificaciones/{id}                 → elimina
 */