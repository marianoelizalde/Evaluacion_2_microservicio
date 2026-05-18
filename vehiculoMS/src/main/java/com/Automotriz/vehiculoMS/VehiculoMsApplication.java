package com.Automotriz.vehiculoMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehiculoMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(VehiculoMsApplication.class, args);
    }
}


//ENDPOINTS 
 //  GET    :8081/api/v1/vehiculos                   → lista todos
 //  GET    :8081/api/v1/vehiculos/disponibles        → solo estado=DISPONIBLE
 //  GET    :8081/api/v1/vehiculos/{id}               → busca por ID
 //  GET    :8081/api/v1/vehiculos/patente/{patente}  → busca por patente
 //  GET    :8081/api/v1/vehiculos/dto/{id}           → * para reservasMS y tarifasMS
 //  POST   :8081/api/v1/vehiculos                    → crea vehículo
 //  PUT    :8081/api/v1/vehiculos/{id}               → actualiza
 //  DELETE :8081/api/v1/vehiculos/{id}               → elimina
 //ENDPOINTS 
 //  GET    :8081/api/v1/mantenciones                          → lista todas
 //  GET    :8081/api/v1/mantenciones/{id}                     → busca por ID
 //  GET    :8081/api/v1/mantenciones/vehiculo/{vehiculoId}    → mantenciones del vehículo
 //  POST   :8081/api/v1/mantenciones                          → crea (body: fechaIngreso, costo, vehiculo:{id:1})
 //  PUT    :8081/api/v1/mantenciones/{id}                     → actualiza
 //  DELETE :8081/api/v1/mantenciones/{id}                     → elimina
 