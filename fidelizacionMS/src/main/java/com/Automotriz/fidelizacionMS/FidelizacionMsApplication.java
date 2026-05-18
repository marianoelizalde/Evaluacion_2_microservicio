package com.Automotriz.fidelizacionMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FidelizacionMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FidelizacionMsApplication.class, args);
    }
}

/**
 *
 *  * FEIGN CLIENT:
 *  UsuarioClient → GET :8080/api/v1/usuarios/dto/{id} → UsuarioDTO
 *
 *  GET    :8088/api/v1/fidelizacion                       → lista todos
 *  GET    :8088/api/v1/fidelizacion/{id}                  → busca por ID
 *  GET    :8088/api/v1/fidelizacion/usuario/{usuarioId}   → puntos de ese usuario
 *  GET    :8088/api/v1/fidelizacion/{id}/detalle          → ⭐ fidelizacion + datos del usuario via Feign
 *  POST   :8088/api/v1/fidelizacion                       → crea
 *         body: { usuarioId: 1, puntosAcumulados: 0, nivelSocio: "BRONCE" }
 *  PUT    :8088/api/v1/fidelizacion/{id}                  → actualiza
 *  PUT    :8088/api/v1/fidelizacion/{id}/sumar/{puntos}   → ⭐ suma puntos
 *  DELETE :8088/api/v1/fidelizacion/{id}                  → elimina
 */