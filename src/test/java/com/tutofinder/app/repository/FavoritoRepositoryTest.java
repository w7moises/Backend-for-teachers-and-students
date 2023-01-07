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
public class FavoritoRepositoryTest {

    @Autowired
    private FavoritoRepository underTest;

    @Autowired
    private DocenteRepository underTest2;

    @Autowired
    private PadreRepository underTest3;

    @Test
    void itShouldGetFavoritoById() {
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
        List Alumnos = new ArrayList<Alumno>();


        Docente docente = new Docente(id, nombre, apellido, dni, domicilio, correo, numeroCuenta, costoHora, membresia, foto, tutorias, fecha);
        underTest2.save(docente);

        Padre padre = new Padre(id, nombre, apellido, dni, correo, foto, fecha, Alumnos);
        underTest3.save(padre);

        Optional<Padre> searchPadre = underTest3.findById(padre.getId());
        Optional<Docente> searchDocente = underTest2.findById(docente.getId());


        Favorito favorito = new Favorito(id, searchPadre.get(), searchDocente.get());
        underTest.save(favorito);

        Optional<Favorito> optionalfavorito = underTest.findById(id);

        assertThat(optionalfavorito).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualToIgnoringGivenFields(favorito, "createAt");
                }
        );

    }

    @Test
    void itShouldGetFavoritoById1() {
        Long id = 1L;
        Docente docente = new Docente();
        Padre padre = new Padre();

        Favorito favorito = new Favorito(id, padre, docente);

        underTest.save(favorito);

        Optional<Favorito> optionalFavorito = underTest.findById(id);

        assertThat(optionalFavorito).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualTo(favorito);
                }
        );

    }


    @Test
    void itShouldNotSaveFavoritoWhenPadreIsNull() {

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
        List Alumnos = new ArrayList<Alumno>();


        Docente docente = new Docente(id, nombre, apellido, dni, domicilio, correo, numeroCuenta, costoHora, membresia, foto, tutorias, fecha);


        Favorito favorito = new Favorito(id, null, docente);


        assertThatThrownBy(() -> underTest.save(favorito))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Favorito.padre")
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
