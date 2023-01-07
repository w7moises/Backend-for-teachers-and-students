package com.tutofinder.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "tarjetas")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El número de tarjeta no puede estar vacío")
    @Column(name = "numero_tarjeta", nullable = false, length = 16)
    private String numeroTarjeta;

    @NotEmpty(message = "La fecha de expiración no puede estar vacía")
    @Column(name = "fecha_expiracion", nullable = false)
    private String fechaExpiracion;

    @NotEmpty(message = "El nombre del poseedor no puede estar vacío")
    @Column(name = "nombre_poseedor", nullable = false)
    private String nombrePoseedor;
}