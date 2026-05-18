package com.Automotriz.reservasMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReservasMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservasMsApplication.class, args);
    }
}

/*  FEIGN CLIENTS:
 *  UsuarioClient  → GET :8080/api/v1/usuarios/rut/{rut}    → UsuarioDTO
 *  VehiculoClient → GET :8081/api/v1/vehiculos/patente/{p} → VehiculoDTO
 *  SucursalClient → GET :8082/api/v1/sucursales/dto/{id}   → SucursalDTO
 *
 *  GET    :8083/api/v1/reservas                  → lista todas
 *  GET    :8083/api/v1/reservas/{id}             → busca por ID
 *  GET    :8083/api/v1/reservas/cliente/{rut}    → reservas de ese cliente
 *  GET    :8083/api/v1/reservas/estado/{estado}  → reservas por estado
 *  GET    :8083/api/v1/reservas/{id}/detalle     → *LLAMARA A LOS 3 MS via Feign
 *                                                   devuelve reserva+usuario+vehiculo+sucursal
 *  POST   :8083/api/v1/reservas                  → crea (estado=PENDIENTE automático)
 *         body: { rutCliente, patente, sucursalId, fechaInicio, fechaFin }
 *  PUT    :8083/api/v1/reservas/{id}             → actualiza
 *  DELETE :8083/api/v1/reservas/{id}             → elimina
 */
