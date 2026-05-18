package com.Automotriz.usuarioMS.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Integer id;

    @NotBlank(message = "El tipo de cliente es obligatorio")
    @Pattern(regexp = "^(EMPRESA|NATURAL)$", message = "Tipo de cliente debe ser EMPRESA o NATURAL")
    private String tipoCliente;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    // Datos del usuario (sin claveUnica)
    private Integer usuarioId;
    private String rut;
    private String nombre;
    private String correo;
    private String telefono;
}