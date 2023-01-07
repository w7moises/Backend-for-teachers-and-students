package com.tutofinder.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@Table(name = "membresias")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Membresia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;

    @Column(name = "fecha_expiracion", nullable = false)
    private Date fechaExpiracion;

    @NotEmpty(message = "La descripcion no debe estar vacia")
    @Column(name = "descripcion_membresia", nullable = false)
    private String descripcionMembresia;

    @Column(name = "costo_membresia")
    private double costoMembresia;
}
