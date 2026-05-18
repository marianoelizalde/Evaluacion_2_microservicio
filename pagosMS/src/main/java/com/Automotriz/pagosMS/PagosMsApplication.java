package com.Automotriz.pagosMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PagosMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PagosMsApplication.class, args);
    }
}

/**
 *  TABLA: pago
 *  pago: id, reservaId(Integer←reservasMS), monto(Double),
 *        estadoPago(PENDIENTE/PAGADO/RECHAZADO), fechaPago(String)
 *
 *  GET    :8084/api/v1/pagos                      → lista todos
 *  GET    :8084/api/v1/pagos/{id}                 → busca por ID
 *  GET    :8084/api/v1/pagos/reserva/{reservaId}  → pagos de esa reserva
 *  GET    :8084/api/v1/pagos/estado/{estado}      → pagos por estado
 *  POST   :8084/api/v1/pagos                      → crea (estado=PENDIENTE, fecha=hoy automático)
 *         body: { reservaId: 1, monto: 150000 }
 *  PUT    :8084/api/v1/pagos/{id}                 → actualiza
 *  PUT    :8084/api/v1/pagos/{id}/confirmar       → * estado=PAGADO
 *  PUT    :8084/api/v1/pagos/{id}/rechazar        → * estado=RECHAZADO
 *  DELETE :8084/api/v1/pagos/{id}                 → elimina
 */ 
