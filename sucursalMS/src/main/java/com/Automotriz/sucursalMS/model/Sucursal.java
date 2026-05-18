package com.Automotriz.sucursalMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String nombre;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "La comuna es obligatoria")
    private String comuna;

    @Column
    @PositiveOrZero(message = "La cantidad de empleados debe ser cero o positiva")
    private Integer cantidadEmpleados;
}


/**
 *
 *  TABLA: sucursal
 *  sucursal: id, nombre, direccion, comuna, cantidadEmpleados
 *
 *  GET    :8082/api/v1/sucursales                → lista todas
 *  GET    :8082/api/v1/sucursales/{id}           → busca por ID
 *  GET    :8082/api/v1/sucursales/comuna/{comuna}→ sucursales de esa comuna
 *  GET    :8082/api/v1/sucursales/dto/{id}       → ⭐ para reservasMS
 *  POST   :8082/api/v1/sucursales                → crea sucursal
 *  PUT    :8082/api/v1/sucursales/{id}           → actualiza
 *  DELETE :8082/api/v1/sucursales/{id}           → elimina
 */