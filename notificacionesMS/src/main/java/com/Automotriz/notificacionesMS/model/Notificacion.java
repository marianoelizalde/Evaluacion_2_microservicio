package com.Automotriz.notificacionesMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "El tipo de notificación es obligatorio")
    @Pattern(regexp = "^(EMAIL|SMS|PUSH)$", message = "Tipo inválido. Use EMAIL, SMS o PUSH")
    private String tipo;

    @Column(nullable = false)
    @NotNull(message = "El ID de la reserva es obligatorio")
    @Positive(message = "El ID de reserva debe ser positivo")
    private Integer reservaId;

    @Column(nullable = false, length = 500)
    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 500, message = "El mensaje no puede exceder 500 caracteres")
    private String mensaje;

    @Column(nullable = false)
    private String fechaNotificacion;

    @Column(nullable = false, length = 20)
    private String estado; // PENDIENTE, ENVIADO, FALLIDO
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
