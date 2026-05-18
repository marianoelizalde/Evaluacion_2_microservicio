package com.Automotriz.usuarioMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // 1. ← Importación obligatoria de validaciones
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "antecedentes")
public class Antecedentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // "VIGENTE", "SUSPENDIDA", "VENCIDA"
    @NotBlank(message = "El estado de la licencia es obligatorio") // 2. ← Candado para texto plano
    @Pattern(regexp = "VIGENTE|SUSPENDIDA|VENCIDA", 
             message = "El estado de la licencia debe ser VIGENTE, SUSPENDIDA o VENCIDA") // 3. ← Restricción exacta según tu comentario
    @Column(nullable = false)
    private String estadoLicencia;

    @NotNull(message = "El registro de multas es obligatorio")
    @PositiveOrZero(message = "El registro de multas no puede tener valores negativos") // 4. ← Candado para campos numéricos enteros
    @Column(nullable = false)
    private Integer registroMultas;

    @NotNull(message = "El cliente asociado es obligatorio") // 5. ← Candado para la relación OneToOne
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}

//*ENDPOINTS:
 //*  GET    :8080/api/v1/antecedentes              → lista todos
 //*  GET    :8080/api/v1/antecedentes/{id}         → busca por ID
 //*  GET    :8080/api/v1/antecedentes/cliente/{id} → antecedentes del cliente
 //*  POST   :8080/api/v1/antecedentes              → crea (body: estadoLicencia, registroMultas, cliente:{id:1})
 //*  PUT    :8080/api/v1/antecedentes/{id}         → actualiza (ej: agregar multa)
 //*  DELETE :8080/api/v1/antecedentes/{id}         → elimina
 
