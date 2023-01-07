package com.tutofinder.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "pagos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion_pago")
    @NotEmpty(message = "La descripcion no debe estar vacia")
    private String descripcionPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre_id", nullable = false)
    private Padre padre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;

    @Column(name = "costo_pago")
    private double costoPago;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
}