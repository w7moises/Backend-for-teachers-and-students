package com.tutofinder.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "padres")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Padre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre del padre no puede estar vacio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "El apellido del padre no puede estar vacio")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty(message = "El DNI del padre no puede estar vacio")
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

    @OneToMany(mappedBy = "padre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"padre"}, allowSetters = true)
    private List<Alumno> alumnos;

    @PrePersist
    public void PrePersist() {
        this.createAt = new Date();
    }

    public Integer getFotoHashCode() {
        return (this.foto != null) ? this.foto.hashCode() : null;
    }

}
