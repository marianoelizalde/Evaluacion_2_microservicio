package com.Automotriz.reservasMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El RUT del cliente es obligatorio")
    @Column(nullable = false)
    private String rutCliente;

    @NotBlank(message = "La patente es obligatoria")
    @Column(nullable = false)
    private String patente;

    @NotNull(message = "El ID de sucursal es obligatorio")
    @Column(nullable = false)
    private Integer sucursalId;

    @NotBlank(message = "La fecha de inicio es obligatoria")
    @Column(nullable = false)
    private String fechaInicio;

    @NotBlank(message = "La fecha de fin es obligatoria")
    @Column(nullable = false)
    private String fechaFin;

    @Pattern(regexp = "PENDIENTE|ACTIVA|FINALIZADA|CANCELADA",
             message = "El estado debe ser: PENDIENTE, ACTIVA, FINALIZADA o CANCELADA")
    @Column(nullable = false)
    private String estado;
}
