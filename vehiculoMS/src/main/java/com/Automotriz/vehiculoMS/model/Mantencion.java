package com.Automotriz.vehiculoMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // 1. ← ¡IMPORTACIÓN DE VALIDACIONES!
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mantencion")
public class Mantencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha de ingreso es obligatoria") 
    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @NotNull(message = "El costo es obligatorio")
    @PositiveOrZero(message = "El costo no puede ser un valor negativo") 
    @Column(nullable = false)
    private Double costo;

    @NotNull(message = "El vehículo asociado es obligatorio") 
    @ManyToOne
    @JoinColumn(name = "name = vehiculo_id", nullable = false)
    private Vehiculo vehiculo;
}