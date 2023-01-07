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
public class MembresiaRepositoryTest {

    @Autowired
    private MembresiaRepository underTest;

    @Autowired
    private DocenteRepository underTest2;

    @Autowired
    private TarjetaRepository underTest3;

    @Test
    void itShouldGetMembresiaById() {
        Long id = 1L;
        String descripcionInforme = "Buen trabajo";
        //Docente
        String nombre = "NombreDocente";
        String apellido = "ApellidoDocente";
        String domicilio = "Jr. 28 de julio 491";
        String dni = "19348403";
        String correo = "correoDocente@correo.com";
        String numeroCuenta = "312393123";
        double costoHora = 23.5;
        boolean membresia = true;
        Date fechaExpiracion = new Date();
        String descripcionMembrecia = "Descripcion";
        Double costoMembrecia = 20.0;
        byte[] foto = "Foto".getBytes();
        List tutorias = new ArrayList<Tutoria>();
        Date fecha = new Date();

        Docente docente = new Docente(id, "nombre", "apelido", "jr 28 de agosto 302", "12345678", "correo@correo.com",
                "123123123", 25.5, true, foto, tutorias, fecha);
        underTest2.save(docente);

        Tarjeta tarjeta = new Tarjeta(id, "41241241241", "30-1-23", "NombrePoseedor");
        underTest3.save(tarjeta);


        Optional<Docente> optionalDocente = underTest2.findById(id);
        Optional<Tarjeta> optionalTarjeta = underTest3.findById(id);

        Membresia membresiaEntity = new Membresia(id, optionalDocente.get(), optionalTarjeta.get(), fechaExpiracion, descripcionMembrecia, costoMembrecia);
        underTest.save(membresiaEntity);

        Optional<Membresia> optionalMembresia = underTest.findById(id);

        assertThat(optionalMembresia).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualTo(membresiaEntity);
                }
        );
    }

    @Test
    void itShouldNotSaveMembreciaWhenTarjetaIsNull() {
        Long id = 1L;
        String descripcionInforme = "Buen trabajo";
        //Docente
        String nombre = "NombreDocente";
        String apellido = "ApellidoDocente";
        String domicilio = "Jr. 28 de julio 491";
        String dni = "19348403";
        String correo = "correoDocente@correo.com";
        String numeroCuenta = "312393123";
        double costoHora = 23.5;
        boolean membresia = true;
        Date fechaExpiracion = new Date();
        String descripcionMembrecia = "Descripcion";
        Double costoMembrecia = 20.0;
        byte[] foto = "Foto".getBytes();
        List tutorias = new ArrayList<Tutoria>();
        Date fecha = new Date();

        Docente docente = new Docente(id, "nombre", "apelido", "jr 28 de agosto 302", "12345678", "correo@correo.com",
                "123123123", 25.5, true, foto, tutorias, fecha);

        Membresia membresiaEntity = new Membresia(id, docente, null, fechaExpiracion, descripcionMembrecia, costoMembrecia);

        assertThatThrownBy(() -> underTest.save(membresiaEntity))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Membresia.tarjeta")
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
