package com.Automotriz.usuarioMS.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String descripcion;

    // Solo ID porque sucursal es otro microservicio
    @Column(name = "sucursal_id", nullable = false)
    private Integer sucursalId;

    @Column(nullable = false)
    private String infoBancaria;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}

 //*ENDPOINTS:
 //*  GET    :8080/api/v1/empleados                → lista todos
 //*  GET    :8080/api/v1/empleados/{id}           → busca por ID
 //*  GET    :8080/api/v1/empleados/sucursal/{id}  → empleados de esa sucursal
 //*  GET    :8080/api/v1/empleados/dto/{id}       → * para otros MS
 //*  POST   :8080/api/v1/empleados                → crea (body: descripcion, sucursalId, infoBancaria, usuario:{id:2})
 //*  PUT    :8080/api/v1/empleados/{id}           → actualiza
 //*  DELETE :8080/api/v1/empleados/{id}           → elimina
