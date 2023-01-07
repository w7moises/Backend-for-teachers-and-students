package com.tutofinder.app.repository;


import com.tutofinder.app.entity.Alumno;
import com.tutofinder.app.entity.Curso;
import com.tutofinder.app.entity.Padre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.PrePersist;
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

public class AlumnoRepositoryTest {

    @Autowired
    private AlumnoRepository underTest;


    @Autowired
    private PadreRepository underTest2;

    @Test
    void itShouldGetAlumnoById() {
        Long id = 1L;
        String nombre = "AlumnoNombre";
        String apellido = "AlumnoApellido";
        String dni = "12345678";
        String gradoEstudio = "5to";
        String correo = "alumnoCorreo@coreo.com";
        byte[] foto = "Foto".getBytes();
        Date fecha = new Date();
        Date fecha2 = new Date();
        List myList = new ArrayList<Alumno>();

        Padre padre = new Padre(id, "", "", "", "", foto, fecha, myList);
        underTest2.save(padre);

        Optional<Padre> searchPadre = underTest2.findById(padre.getId());

        Alumno alumno = new Alumno(id, nombre, apellido, gradoEstudio, searchPadre.get(), dni, correo, foto, fecha2);
        underTest.save(alumno);

        Optional<Alumno> optionalAlumno = underTest.findById(id);

        assertThat(optionalAlumno).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualToIgnoringGivenFields(alumno, "createAt");
                }
        );
    }

    @Test
    void itShouldGetAlumnoByNombreWhenNombreDoesNotExists() {
        String nombre = "AlumnoNombre";

        Optional<Alumno> optionalAlumno = underTest.findByNombre(nombre);

        assertThat(optionalAlumno).isNotPresent();
    }

    @Test
    void itShouldNotSaveAlumnoWhenNombreIsNull() {
        Long id = 1L;
        String apellido = "AlumnoApellido";
        String dni = "12345678";
        String gradoEstudio = "5to";
        String correo = "alumnoCorreo@coreo.com";
        byte[] foto = "Foto".getBytes();
        Date fecha = new Date();
        Date fecha2 = new Date();
        List myList = new ArrayList<Alumno>();

        Padre padre = new Padre(id, "", "", "", "", foto, fecha, myList);
        //underTest2.save(padre);

        Optional<Padre> searchPadre = underTest2.findById(padre.getId());

        Alumno alumno = new Alumno(id, null, apellido, gradoEstudio, padre, dni, correo, foto, fecha2);

        assertThatThrownBy(() -> underTest.save(alumno))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Alumno.nombre")
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
