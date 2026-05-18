package com.Automotriz.soporteMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "El ID de la reserva es obligatorio")
    @Positive(message = "El ID de reserva debe ser positivo")
    private Integer reservaId;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El asunto es obligatorio")
    @Size(min = 5, max = 100, message = "El asunto debe tener entre 5 y 100 caracteres")
    private String asunto;

    @Column(nullable = false, length = 500)
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String descripcion;

    @Column(nullable = false, length = 20)
    private String estado; // ABIERTO, EN_PROCESO, CERRADO

    @Column(nullable = false)
    private String fechaCreacion;
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
