package com.Automotriz.usuarioMS.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^[0-9]+-[0-9Kk]$", message = "Formato de RUT inválido. Ejemplo: 12345678-9")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo electrónico inválido")
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[+]?[0-9]{8,15}$", message = "Formato de teléfono inválido")
    private String telefono;

    @NotNull(message = "El tipo es obligatorio (1=CLIENTE, 2=EMPLEADO)")
    @Min(value = 1, message = "El tipo debe ser 1 o 2")
    @Max(value = 2, message = "El tipo debe ser 1 o 2")
    private Integer tipo;
}