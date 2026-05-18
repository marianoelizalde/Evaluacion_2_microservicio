package com.Automotriz.soporteMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SoporteMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoporteMsApplication.class, args);
    }
}

/**
 *
 *  GET    :8086/api/v1/tickets                     → lista todos
 *  GET    :8086/api/v1/tickets/{id}                → busca por ID
 *  GET    :8086/api/v1/tickets/reserva/{reservaId} → tickets de esa reserva
 *  GET    :8086/api/v1/tickets/estado/{estado}     → tickets por estado
 *  POST   :8086/api/v1/tickets                     → crea (estado=ABIERTO automático)
 *         body: { reservaId: 1, asunto: "El vehiculo tiene un rayon" }
 *  PUT    :8086/api/v1/tickets/{id}                → actualiza
 *  PUT    :8086/api/v1/tickets/{id}/cerrar         → * estado=CERRADO
 *  DELETE :8086/api/v1/tickets/{id}                → elimina
 */
