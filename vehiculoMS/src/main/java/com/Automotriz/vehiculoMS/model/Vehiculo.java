package com.Automotriz.vehiculoMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El modelo es obligatorio")
    @Column(nullable = false)
    private String modelo;

    @NotBlank(message = "La marca es obligatoria")
    @Column(nullable = false)
    private String marca;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 2000, message = "El año debe ser mayor a 2000")
    @Max(value = 2030, message = "El año no puede ser mayor a 2030")
    @Column(nullable = false)
    private Integer anio;

    @NotBlank(message = "El estado es obligatorio")
   
    @Pattern(regexp = "DISPONIBLE|ARRENDADO|EN_MANTENCION",
             message = "El estado debe ser: DISPONIBLE, ARRENDADO o EN_MANTENCION")
    @Column(nullable = false)
    private String estado;

    @NotBlank(message = "La patente es obligatoria")
    @Column(nullable = false, unique = true)
    private String patente;
}