package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Alumno;
import com.tutofinder.app.entity.Padre;
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

public class PadreRepositoryTest {

    @Autowired
    private PadreRepository underTest;

    @Test
    void itShouldGetPadreById() {
        Long id = 1L;
        String nombre = "PadreNombre";
        String apellido = "PadreApellido";
        String dni = "12345678";
        String correo = "alumnoCorreo@coreo.com";
        byte[] foto = "Foto".getBytes();
        Date fecha = new Date();
        List Alumnos = new ArrayList<Alumno>();

        Padre padre = new Padre(id, nombre, apellido, dni, correo, foto, fecha, Alumnos);
        underTest.save(padre);

        Optional<Padre> optionalPadre = underTest.findById(id);

        assertThat(optionalPadre).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualToIgnoringGivenFields(padre, "createAt");
                }
        );
    }


    @Test
    void itShouldGetPadreByNombreWhenNombreDoesNotExists() {

        String nombre = "PadreNombre";

        Optional<Padre> optinalPadre = underTest.findByNombre(nombre);

        assertThat(optinalPadre).isNotPresent();
    }

    @Test
    void itShouldNotSavePadreWhenNombreIsNull() {
        Long id = 1L;

        String apellido = "PadreApellido";
        String dni = "12345678";
        String correo = "alumnoCorreo@coreo.com";
        byte[] foto = "Foto".getBytes();
        Date fecha = new Date();
        List Alumnos = new ArrayList<Alumno>();

        Padre padre = new Padre(id, null, apellido, dni, correo, foto, fecha, Alumnos);


        assertThatThrownBy(() -> underTest.save(padre))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Padre.nombre")
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
