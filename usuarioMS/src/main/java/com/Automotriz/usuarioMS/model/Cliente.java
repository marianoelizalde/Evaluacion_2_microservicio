package com.Automotriz.usuarioMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // 1. ← Importamos las anotaciones de validación
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // "EMPRESA" o "NATURAL"
    @NotBlank(message = "El tipo de cliente es obligatorio")
    @Pattern(regexp = "EMPRESA|NATURAL", message = "El tipo de cliente debe ser EMPRESA o NATURAL") // 2. ← Restringimos a solo estas dos opciones
    @Column(nullable = false)
    private String tipoCliente;

    @Column
    private String descripcion;

    @NotNull(message = "El usuario asociado es obligatorio") 
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}