package com.tutofinder.app.service;


import com.tutofinder.app.dto.InformeDto;
import com.tutofinder.app.dto.create.CreateInformeDto;
import com.tutofinder.app.entity.Alumno;
import com.tutofinder.app.entity.Informe;
import com.tutofinder.app.entity.Tutoria;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.repository.AlumnoRepository;
import com.tutofinder.app.repository.InformeRepository;
import com.tutofinder.app.repository.TutoriaRepository;
import com.tutofinder.app.services.impl.InformeServiceImpl;
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

public class InformeServiceTest {
    private static final Long INFORME_ID = 1L;
    private static final String DESCRIPCION_INFORME = "bueno";
    private static final Long TUTORIA_ID = 1L;
    private static final Long ALUMNO_ID = 1L;
    public static final Informe INFORME = new Informe();

    CreateInformeDto CREATE_INFORME_DTO = new CreateInformeDto();
    private static final String INFORME_DELETED = "INFORME_DELETED";
    private static final Optional<Informe> OPTIONAL_INFORME_EMPTY = Optional.empty();
    private static final Optional<Informe> OPTIONAL_INFORME = Optional.of(INFORME);
    private static final Optional<Tutoria> OPTIONAL_TUTORIA = Optional.of(new Tutoria());
    private static final Optional<Alumno> OPTIONAL_ALUMNO = Optional.of(new Alumno());

    @Mock
    InformeRepository informeRepository;

    @Mock
    TutoriaRepository tutoriaRepository;

    @Mock
    AlumnoRepository alumnoRepository;

    @InjectMocks
    InformeServiceImpl informeServiceImpl;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        INFORME.setId(INFORME_ID);
        INFORME.setDescripcionInforme(DESCRIPCION_INFORME);

        CREATE_INFORME_DTO.setDescripcionInforme(DESCRIPCION_INFORME);
        CREATE_INFORME_DTO.setTutoriaId(TUTORIA_ID);
        CREATE_INFORME_DTO.setAlumnoId(ALUMNO_ID);
    }

    @Test
    public void getInformeByIdTest() throws BookingException {
        Mockito.when(informeRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_INFORME);
        informeServiceImpl.getInformeById(INFORME_ID);
    }

    @Test
    public void getInformesTest() throws BookingException {
        final Informe informe = new Informe();
        Mockito.when(informeRepository.findAll()).thenReturn(Arrays.asList(informe));
        final List<InformeDto> response = informeServiceImpl.getInformes();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test(expected = BookingException.class)
    public void getInformeByIdTestError() throws BookingException {
        Mockito.when(informeRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_INFORME_EMPTY);
        informeServiceImpl.getInformeById(INFORME_ID);
        fail();
    }


    @Test
    public void createInformeTest() throws BookingException {
        Mockito.when(tutoriaRepository.findById(TUTORIA_ID)).thenReturn(OPTIONAL_TUTORIA);
        Mockito.when(alumnoRepository.findById(ALUMNO_ID)).thenReturn(OPTIONAL_ALUMNO);
        Mockito.when(informeRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_INFORME);
        Mockito.when(informeRepository.save(Mockito.any(Informe.class))).thenReturn(INFORME);
        informeServiceImpl.createInforme(CREATE_INFORME_DTO);
    }

    @Test(expected = MockitoException.class)
    public void createInformeInternalServerErrorTest() throws BookingException {
        Mockito.when(informeRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_INFORME);
        Mockito.doThrow(Exception.class).when(informeRepository).save(Mockito.any(Informe.class));
        informeServiceImpl.createInforme(CREATE_INFORME_DTO);
        fail();
    }

    @Test
    public void deleteInformeOk() throws BookingException {
        Mockito.when(informeRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_INFORME);
        final String response = informeServiceImpl.deleteInforme(INFORME_ID);
        assertEquals(response, INFORME_DELETED);
    }

    @Test(expected = BookingException.class)
    public void deleteInformeNotFountError() throws BookingException {
        Mockito.when(informeRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_INFORME_EMPTY);
        final String response = informeServiceImpl.deleteInforme(INFORME_ID);
        assertEquals(response, INFORME_DELETED);
        fail();
    }

    @Test(expected = MockitoException.class)
    public void deleteInformeInternalServerError() throws BookingException {
        Mockito.when(informeRepository.findById(INFORME_ID)).thenReturn(OPTIONAL_INFORME);
        Mockito.doThrow(Exception.class).when(informeRepository).deleteById(INFORME_ID);
        final String response = informeServiceImpl.deleteInforme(INFORME_ID);
        assertEquals(response, INFORME_DELETED);
        fail();
    }
}
