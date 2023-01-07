package com.tutofinder.app.service;

import com.tutofinder.app.dto.MembresiaDto;
import com.tutofinder.app.dto.create.CreateMembresiaDto;
import com.tutofinder.app.entity.Docente;
import com.tutofinder.app.entity.Membresia;
import com.tutofinder.app.entity.Tarjeta;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.repository.DocenteRepository;
import com.tutofinder.app.repository.MembresiaRepository;
import com.tutofinder.app.repository.TarjetaRepository;
import com.tutofinder.app.services.impl.MembresiaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.Assert.*;

public class MembresiaServiceTest {
    private static final Long MEMBRESIA_ID = 1L;
    private static final String DESCRIPCION_MEMBRESIA = "bueno";
    private static final Double COSTO_MEMBRESIA = 45.8;
    private static final Date FECHA_EXPIRACION = now();
    private static final Long TARJETA_ID = 1L;
    private static final Long DOCENTE_ID = 1L;
    public static final Membresia MEMBRESIA = new Membresia();

    CreateMembresiaDto CREATE_MEMBRESIA_DTO = new CreateMembresiaDto();
    private static final String MEMBRESIA_DELETED = "INFORME_DELETED";
    private static final Optional<Membresia> OPTIONAL_INFORME_EMPTY = Optional.empty();
    private static final Optional<Membresia> OPTIONAL_MEMBRESIA = Optional.of(MEMBRESIA);
    private static final Optional<Tarjeta> OPTIONAL_TARJETA = Optional.of(new Tarjeta());
    private static final Optional<Docente> OPTIONAL_DOCENTE = Optional.of(new Docente());

    @Mock
    MembresiaRepository membresiaRepository;

    @Mock
    TarjetaRepository tarjetaRepository;

    @Mock
    DocenteRepository docenteRepository;

    @InjectMocks
    MembresiaServiceImpl membresiaServiceImpl;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        MEMBRESIA.setId(MEMBRESIA_ID);
        MEMBRESIA.setDescripcionMembresia(DESCRIPCION_MEMBRESIA);

        CREATE_MEMBRESIA_DTO.setDescripcionMembresia(DESCRIPCION_MEMBRESIA);
        CREATE_MEMBRESIA_DTO.setCostoMembresia(COSTO_MEMBRESIA);
        CREATE_MEMBRESIA_DTO.setFechaExpiracion(FECHA_EXPIRACION);
        CREATE_MEMBRESIA_DTO.setTarjetaId(TARJETA_ID);
        CREATE_MEMBRESIA_DTO.setDocenteId(DOCENTE_ID);

    }

    @Test
    public void getMembresiaByIdTest() throws BookingException {
        Mockito.when(membresiaRepository.findById(MEMBRESIA_ID)).thenReturn(OPTIONAL_MEMBRESIA);
        membresiaServiceImpl.getMembresiaById(MEMBRESIA_ID);
    }

    @Test
    public void getMembresiaTest() throws BookingException {
        final Membresia membresia = new Membresia();
        Mockito.when(membresiaRepository.findAll()).thenReturn(Arrays.asList(membresia));
        final List<MembresiaDto> response = membresiaServiceImpl.getMembresias();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test(expected = BookingException.class)
    public void getMembresiaByIdTestError() throws BookingException {
        Mockito.when(membresiaRepository.findById(MEMBRESIA_ID)).thenReturn(OPTIONAL_INFORME_EMPTY);
        membresiaServiceImpl.getMembresiaById(MEMBRESIA_ID);
        fail();
    }

    @Test
    public void createMembresiaTest() throws BookingException {
        Mockito.when(tarjetaRepository.findById(TARJETA_ID)).thenReturn(OPTIONAL_TARJETA);
        Mockito.when(docenteRepository.findById(DOCENTE_ID)).thenReturn(OPTIONAL_DOCENTE);
        Mockito.when(membresiaRepository.findById(MEMBRESIA_ID)).thenReturn(OPTIONAL_MEMBRESIA);
        Mockito.when(membresiaRepository.save(Mockito.any(Membresia.class))).thenReturn(MEMBRESIA);
        membresiaServiceImpl.createMembresia(CREATE_MEMBRESIA_DTO);
    }

    @Test(expected = MockitoException.class)
    public void createMembresiaInternalServerErrorTest() throws BookingException {
        Mockito.when(membresiaRepository.findById(MEMBRESIA_ID)).thenReturn(OPTIONAL_MEMBRESIA);
        Mockito.doThrow(Exception.class).when(membresiaRepository).save(Mockito.any(Membresia.class));
        membresiaServiceImpl.createMembresia(CREATE_MEMBRESIA_DTO);
        fail();
    }

    @Test
    public void deleteMembresiaOk() throws BookingException {
        Mockito.when(membresiaRepository.findById(MEMBRESIA_ID)).thenReturn(OPTIONAL_MEMBRESIA);
        final String response = membresiaServiceImpl.deleteMembresia(MEMBRESIA_ID);
        assertEquals(response, MEMBRESIA_DELETED);
    }

    @Test(expected = BookingException.class)
    public void deleteMembresiaNotFountError() throws BookingException {
        Mockito.when(membresiaRepository.findById(MEMBRESIA_ID)).thenReturn(OPTIONAL_INFORME_EMPTY);
        final String response = membresiaServiceImpl.deleteMembresia(MEMBRESIA_ID);
        assertEquals(response, MEMBRESIA_DELETED);
        fail();
    }

    @Test(expected = MockitoException.class)
    public void deleteMembresiaInternalServerError() throws BookingException {
        Mockito.when(membresiaRepository.findById(MEMBRESIA_ID)).thenReturn(OPTIONAL_MEMBRESIA);
        Mockito.doThrow(Exception.class).when(membresiaRepository).deleteById(MEMBRESIA_ID);
        final String response = membresiaServiceImpl.deleteMembresia(MEMBRESIA_ID);
        assertEquals(response, MEMBRESIA_DELETED);
        fail();
    }

}
