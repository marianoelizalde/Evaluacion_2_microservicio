package com.Automotriz.fidelizacionMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fidelizacion")
public class Fidelizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 12)
    @NotBlank(message = "El RUT del cliente es obligatorio")
    @Pattern(regexp = "^[0-9]+-[0-9Kk]$", message = "Formato de RUT inválido")
    private String rutCliente;

    @Column(nullable = false)
    @PositiveOrZero(message = "Los puntos acumulados no pueden ser negativos")
    private Integer puntosAcumulados;

    @Column(nullable = false, length = 20)
    private String nivelSocio; // BRONCE, PLATA, ORO, PLATINO
}

/**
 *
 *  * FEIGN CLIENT:
 *  UsuarioClient → GET :8080/api/v1/usuarios/dto/{id} → UsuarioDTO
 *
 *  GET    :8088/api/v1/fidelizacion                       → lista todos
 *  GET    :8088/api/v1/fidelizacion/{id}                  → busca por ID
 *  GET    :8088/api/v1/fidelizacion/usuario/{usuarioId}   → puntos de ese usuario
 *  GET    :8088/api/v1/fidelizacion/{id}/detalle          → * fidelizacion + datos del usuario via Feign
 *  POST   :8088/api/v1/fidelizacion                       → crea
 *         body: { usuarioId: 1, puntosAcumulados: 0, nivelSocio: "BRONCE" }
 *  PUT    :8088/api/v1/fidelizacion/{id}                  → actualiza
 *  PUT    :8088/api/v1/fidelizacion/{id}/sumar/{puntos}   → * suma puntos
 *  DELETE :8088/api/v1/fidelizacion/{id}                  → elimina
 */