package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Tarjeta;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class TarjetaRepositoryTest {
    @Autowired
    private TarjetaRepository underTest;

    @Test
    void itShouldGetTarjetaById() {
        Long id = 1L;
        String numeroTarjeta = "1234123412341234";
        String fechaExpiracion = "05/21";
        String nombrePoseedor = "Walter Molina";

        Tarjeta tarjeta = new Tarjeta(id, numeroTarjeta, fechaExpiracion, nombrePoseedor);

        underTest.save(tarjeta);

        Optional<Tarjeta> optionalTarjeta = underTest.findById(id);

        assertThat(optionalTarjeta).isPresent().hasValueSatisfying(
                c -> {
                    assertThat(c).isEqualTo(tarjeta);
                }
        );
    }

    @Test
    void itShouldGetTarjetaByNombreWhenNombrePoseedorDoesNotExists() {
        String nombrePoseedor = "Walter Molina";

        List<Tarjeta> optionalTarjeta = underTest.findByNombrePoseedor(nombrePoseedor);

        assertThat(optionalTarjeta).isEmpty();
    }

    @Test
    void itShouldNotSaveTarjetaWhenNombrePoseedorIsNull() {
        Long id = 1L;
        String numeroTarjeta = "1234123412341234";
        String fechaExpiracion = "05/21";

        Tarjeta tarjeta = new Tarjeta(id, numeroTarjeta, fechaExpiracion, null);

        assertThatThrownBy(() -> underTest.save(tarjeta))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Tarjeta.nombrePoseedor")
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveTarjetaWhenFechaExpiracionIsNull() {
        Long id = 1L;
        String numeroTarjeta = "1234123412341234";
        String nombrePoseedor = "Walter Molina";

        Tarjeta tarjeta = new Tarjeta(id, numeroTarjeta, null, nombrePoseedor);

        assertThatThrownBy(() -> underTest.save(tarjeta))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Tarjeta.fechaExpiracion")
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveTarjetaWhenNumeroTarjetaIsNull() {
        Long id = 1L;
        String fechaExpiracion = "05/21";
        String nombrePoseedor = "Walter Molina";

        Tarjeta tarjeta = new Tarjeta(id, null, fechaExpiracion, nombrePoseedor);

        assertThatThrownBy(() -> underTest.save(tarjeta))
                .hasMessageContaining("not-null property references a null or transient value : com.tutofinder.app.entity.Tarjeta.numeroTarjeta")
                .isInstanceOf(DataIntegrityViolationException.class);
    }


}
