package com.tutofinder.app.service;


import com.tutofinder.app.dto.TarjetaDto;
import com.tutofinder.app.dto.create.CreateTarjetaDto;
import com.tutofinder.app.entity.Tarjeta;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.repository.TarjetaRepository;
import com.tutofinder.app.services.impl.TarjetaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TarjetaServiceTest {
    private static final Long TARJETA_ID = 1L;
    private static final String NUMERO_TARJETA = "1234123412341234";
    private static final String FECHA_EXPIRACION = "05/22";
    private static final String NOMBRE_POSEEDOR = "Walter Molina";
    public static final Tarjeta TARJETA = new Tarjeta();

    CreateTarjetaDto CREATE_TARJETA_DTO = new CreateTarjetaDto();

    private static final String TARJETA_DELETED = "TARJETA_DELETED";
    private static final Optional<Tarjeta> OPTIONAL_TARJETA_EMPTY = Optional.empty();
    private static final Optional<Tarjeta> OPTIONAL_TARJETA = Optional.of(TARJETA);

    @Mock
    TarjetaRepository tarjetaRepository;

    @InjectMocks
    TarjetaServiceImpl tarjetaServiceImpl;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        TARJETA.setId(TARJETA_ID);
        TARJETA.setNumeroTarjeta(NUMERO_TARJETA);
        TARJETA.setFechaExpiracion(FECHA_EXPIRACION);
        TARJETA.setNombrePoseedor(NOMBRE_POSEEDOR);

        CREATE_TARJETA_DTO.setNumeroTarjeta(NUMERO_TARJETA);
        CREATE_TARJETA_DTO.setFechaExpiracion(FECHA_EXPIRACION);
        CREATE_TARJETA_DTO.setNombrePoseedor(NOMBRE_POSEEDOR);
    }

    @Test
    public void getTarjetaByIdTest() throws BookingException {
        Mockito.when(tarjetaRepository.findById(TARJETA_ID)).thenReturn(OPTIONAL_TARJETA);
        tarjetaServiceImpl.getTarjetaById(TARJETA_ID);
    }

    @Test
    public void getTarjetasTest() throws BookingException {
        final Tarjeta tarjeta = new Tarjeta();
        Mockito.when(tarjetaRepository.findAll()).thenReturn(Arrays.asList(tarjeta));
        final List<TarjetaDto> response = tarjetaServiceImpl.getTarjetas();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test(expected = BookingException.class)
    public void getTarjetaByIdTestError() throws BookingException {
        Mockito.when(tarjetaRepository.findById(TARJETA_ID)).thenReturn(OPTIONAL_TARJETA_EMPTY);
        tarjetaServiceImpl.getTarjetaById(TARJETA_ID);
        fail();
    }

    @Test
    public void createTarjetaTest() throws BookingException {
        Mockito.when(tarjetaRepository.findById(TARJETA_ID)).thenReturn(OPTIONAL_TARJETA);
        Mockito.when(tarjetaRepository.save(Mockito.any(Tarjeta.class))).thenReturn(TARJETA);
        tarjetaServiceImpl.createTarjeta(CREATE_TARJETA_DTO);
    }

    @Test(expected = MockitoException.class)
    public void createTarjetaInternalServerErrorTest() throws BookingException {
        Mockito.when(tarjetaRepository.findById(TARJETA_ID)).thenReturn(OPTIONAL_TARJETA);
        Mockito.doThrow(Exception.class).when(tarjetaRepository).save(Mockito.any(Tarjeta.class));
        tarjetaServiceImpl.createTarjeta(CREATE_TARJETA_DTO);
        fail();
    }

    @Test
    public void deleteTarjetaOk() throws BookingException {
        Mockito.when(tarjetaRepository.findById(TARJETA_ID)).thenReturn(OPTIONAL_TARJETA);
        final String response = tarjetaServiceImpl.deleteTarjeta(TARJETA_ID);
        assertEquals(response, TARJETA_DELETED);
    }

    @Test(expected = BookingException.class)
    public void deleteTarjetaNotFountError() throws BookingException {
        Mockito.when(tarjetaRepository.findById(TARJETA_ID)).thenReturn(OPTIONAL_TARJETA_EMPTY);
        final String response = tarjetaServiceImpl.deleteTarjeta(TARJETA_ID);
        assertEquals(response, TARJETA_DELETED);
        fail();
    }

    @Test(expected = MockitoException.class)
    public void deleteTarjetaInternalServerError() throws BookingException {
        Mockito.when(tarjetaRepository.findById(TARJETA_ID)).thenReturn(OPTIONAL_TARJETA);
        Mockito.doThrow(Exception.class).when(tarjetaRepository).deleteById(TARJETA_ID);
        final String response = tarjetaServiceImpl.deleteTarjeta(TARJETA_ID);
        assertEquals(response, TARJETA_DELETED);
        fail();
    }

}
