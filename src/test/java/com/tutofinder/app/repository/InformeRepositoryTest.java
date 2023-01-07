package com.tutofinder.app.repository;

import com.tutofinder.app.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class InformeRepositoryTest {
    @Autowired
    private InformeRepository underTest;

    @Test
    void itShouldGetInformeById() {
        Long id = 1L;
        String descripcionInforme = "Buen trabajo";
        Alumno alumno = new Alumno();
        Tutoria tutoria = new Tutoria();

        Informe informe = new Informe(id, descripcionInforme, tutoria, alumno);

        underTest.save(informe);

        Optional<Informe> optionalInforme = underTest.findById(id);

        assertThat(optionalInforme).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualTo(informe);
                }
        );
    }

    @Test
    void itShouldNotSaveInformeWhenDescripcionInformeIsNull() {
        Long id = 1L;
        Alumno alumno = new Alumno();
        Tutoria tutoria = new Tutoria();

        Informe informe = new Informe(id, null, tutoria, alumno);

        assertThatThrownBy(() -> underTest.save(informe))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Informe.descripcionInforme")
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
