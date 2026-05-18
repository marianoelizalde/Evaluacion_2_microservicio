package com.Automotriz.pagosMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "El ID de la reserva es obligatorio")
    @Positive(message = "El ID de reserva debe ser positivo")
    private Integer reservaId;

    @Column(nullable = false)
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private Double monto;

    @Column(nullable = false)
    @NotBlank(message = "La fecha de pago es obligatoria")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Formato de fecha inválido. Use YYYY-MM-DD")
    private String fechaPago;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "El método de pago es obligatorio")
    @Pattern(regexp = "^(DEBITO|CREDITO|TRANSFERENCIA|EFECTIVO)$", 
             message = "Método de pago inválido")
    private String metodoPago;

    @Column(nullable = false, length = 20)
    private String estadoPago; // PENDIENTE, PAGADO, RECHAZADO
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