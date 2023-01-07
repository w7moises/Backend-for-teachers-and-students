package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class CursoRepositoryTest {
    @Autowired
    private CursoRepository underTest;

    @Test
    void itShouldGetCursoById() {
        Long id = 1L;
        String nombre = "Fisica";

        Curso curso = new Curso(id, nombre);

        underTest.save(curso);

        Optional<Curso> optionalCurso = underTest.findById(id);

        assertThat(optionalCurso).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualToComparingFieldByField(curso);
                }
        );
    }

    @Test
    void itShouldGetCursoByNombreWhenNombreDoesNotExists() {
        String nombre = "Fisica";

        Optional<Curso> optionalCurso = underTest.findByNombre(nombre);

        assertThat(optionalCurso).isNotPresent();
    }

    @Test
    void itShouldNotSaveCursoWhenNombreIsNull() {
        Long id = 1L;

        Curso curso = new Curso(id, null);

        assertThatThrownBy(() -> underTest.save(curso))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Curso.nombre")
                .isInstanceOf(DataIntegrityViolationException.class);
    }

}
