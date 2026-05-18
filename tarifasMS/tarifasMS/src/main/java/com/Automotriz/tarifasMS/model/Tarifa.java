package com.Automotriz.tarifasMS.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarifa")
public class Tarifa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer vehiculoId;
    
    @Column(nullable = false)
    private Double precioDia;

    @Column(nullable = false)
    private String temporada;

    @Column(nullable = false)
    private String estado;


}
