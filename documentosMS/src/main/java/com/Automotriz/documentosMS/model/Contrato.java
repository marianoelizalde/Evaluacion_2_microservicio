package com.Automotriz.documentosMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "El ID de la reserva es obligatorio")
    @Positive(message = "El ID de reserva debe ser positivo")
    private Integer reservaId;

    @Column(nullable = false)
    @NotBlank(message = "La fecha de emisión es obligatoria")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Formato de fecha inválido. Use YYYY-MM-DD")
    private String fechaEmision;

    @Column(nullable = false, length = 20)
    private String estado; // PENDIENTE, FIRMADO, CANCELADO

    @Column(length = 1000)
    @Size(max = 1000, message = "Las cláusulas no pueden exceder 1000 caracteres")
    private String clausulas;
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

