package com.Automotriz.documentosMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DocumentosMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DocumentosMsApplication.class, args);
    }
}
/**
 *
 *  GET    :8085/api/v1/contratos                     → lista todos
 *  GET    :8085/api/v1/contratos/{id}                → busca por ID
 *  GET    :8085/api/v1/contratos/reserva/{reservaId} → contratos de esa reserva
 *  GET    :8085/api/v1/contratos/estado/{estado}     → contratos por estado
 *  POST   :8085/api/v1/contratos                     → crea (estado=BORRADOR automático)
 *         body: { reservaId, firmas, participantes, clausula }
 *  PUT    :8085/api/v1/contratos/{id}                → actualiza
 *  PUT    :8085/api/v1/contratos/{id}/firmar         → * estado=FIRMADO
 *  PUT    :8085/api/v1/contratos/{id}/anular         → * estado=ANULADO
 *  DELETE :8085/api/v1/contratos/{id}                → elimina
 */
