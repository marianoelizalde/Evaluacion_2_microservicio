package com.Automotriz.tarifasMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  // ← ¿tienes esta línea?
public class TarifasMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TarifasMsApplication.class, args);
    }
}



/**
 *
 *  * FEIGN CLIENT:
 *  VehiculoClient → GET :8081/api/v1/vehiculos/dto/{id} → VehiculoDTO
 *
 *  GET    :8089/api/v1/tarifas                         → lista todas
 *  GET    :8089/api/v1/tarifas/activas                 → solo estado=ACTIVO
 *  GET    :8089/api/v1/tarifas/{id}                    → busca por ID
 *  GET    :8089/api/v1/tarifas/vehiculo/{vehiculoId}   → tarifas de ese vehiculo
 *  GET    :8089/api/v1/tarifas/temporada/{temporada}   → tarifas por temporada
 *  GET    :8089/api/v1/tarifas/{id}/detalle            → * tarifa + datos vehiculo via Feign
 *  POST   :8089/api/v1/tarifas                         → crea
 *         body: { vehiculoId: 1, precioDia: 45000, temporada: "NORMAL", estado: "ACTIVO" }
 *  PUT    :8089/api/v1/tarifas/{id}                    → actualiza
 *  PUT    :8089/api/v1/tarifas/{id}/activar            → * estado=ACTIVO
 *  PUT    :8089/api/v1/tarifas/{id}/desactivar         → * estado=INACTIVO
 *  DELETE :8089/api/v1/tarifas/{id}                    → elimina
 */