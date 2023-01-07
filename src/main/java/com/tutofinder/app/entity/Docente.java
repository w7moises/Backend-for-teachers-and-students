package com.tutofinder.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "docentes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre del docente no puede estar vacio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "El apellido del docente no puede estar vacio")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty(message = "El DNI del docente no puede estar vacio")
    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String domicilio;

    @Email(message = "El correo no puede ser vacio")
    @Column(unique = true, nullable = false)
    private String correo;

    @Column(unique = true, nullable = false, name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "costo_hora", nullable = false)
    private double CostoHora;

    private Boolean membresia;

    @Lob
    @JsonIgnore
    private byte[] foto;

    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"docente"}, allowSetters = true)
    private List<Tutoria> tutorias;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void PrePersist() {
        this.createAt = new Date();
    }

    public Integer getFotoHashCode() {
        return (this.foto != null) ? this.foto.hashCode() : null;
    }
}
