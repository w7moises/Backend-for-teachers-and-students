package com.tutofinder.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "alumnos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre del alumno no debe estar vacio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "El apellido del alumno no debe estar vacio")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty(message = "El grado de estudio del alumno no debe estar vacio")
    @Column(name = "grado_estudio", nullable = false)
    private String gradoEstudio;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre_id", nullable = false)
    @NotNull(message = "Debe de haber un padre")
    private Padre padre;

    @NotEmpty(message = "El dni no debe estar vacio")
    @Column(unique = true, nullable = false, length = 8)
    private String dni;

    @NotEmpty(message = "El correo no debe estar vacio")
    @Email(message = "Debe tener @")
    @Column(unique = true, nullable = false)
    private String correo;

    @Lob
    @JsonIgnore
    private byte[] foto;

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
