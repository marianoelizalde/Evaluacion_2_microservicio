package com.Automotriz.reservasMS.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDetalleDTO {
    private Integer id;
    private String estado;
    private String fechaInicio; // String en vez de LocalDate
    private String fechaFin;   // String en vez de LocalDate
    private UsuarioDTO cliente;
    private VehiculoDTO vehiculo;
    private SucursalDTO sucursal;
}
