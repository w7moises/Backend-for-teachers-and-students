package com.tutofinder.app.repository;

import com.tutofinder.app.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class DocenteRepositoryTest {
    @Autowired
    private DocenteRepository underTest;

    @Test
    void itShouldGetDocenteById() {
        Long id = 1L;
        String nombre = "NombreDocente";
        String apellido = "ApellidoDocente";
        String domicilio = "Jr. 28 de julio 491";
        String dni = "19348403";
        String correo = "correoDocente@correo.com";
        String numeroCuenta = "312393123";
        double costoHora = 23.5;
        boolean membresia = true;
        byte[] foto = "Foto".getBytes();
        List tutorias = new ArrayList<Tutoria>();
        Date fecha = new Date();

        Docente docente = new Docente(id, nombre, apellido, dni, domicilio, correo, numeroCuenta, costoHora, membresia, foto, tutorias, fecha);

        underTest.save(docente);

        Optional<Docente> optionalDocente = underTest.findById(id);

        assertThat(optionalDocente).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualToIgnoringGivenFields(docente, "createAt");
                }
        );
    }

    @Test
    void itShouldGetDocenteByNombreWhenNombreDoesNotExists() {
        String nombre = "DocenteNombre";

        Optional<Docente> optionalDocente = underTest.findByNombre(nombre);

        assertThat(optionalDocente).isNotPresent();
    }

    @Test
    void itShouldNotSaveDocenteWhenNombreIsNull() {
        Long id = 1L;

        String apellido = "ApellidoDocente";
        String domicilio = "Jr. 28 de julio 491";
        String dni = "19348403";
        String correo = "correoDocente@correo.com";
        String numeroCuenta = "312393123";
        double costoHora = 23.5;
        boolean membresia = true;
        byte[] foto = "Foto".getBytes();
        List tutorias = new ArrayList<Tutoria>();
        Date fecha = new Date();


        Docente docente = new Docente(id, null, apellido, dni, domicilio, correo, numeroCuenta, costoHora, membresia, foto, tutorias, fecha);

        assertThatThrownBy(() -> underTest.save(docente))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Docente.nombre")
                .isInstanceOf(DataIntegrityViolationException.class);


    }
}
