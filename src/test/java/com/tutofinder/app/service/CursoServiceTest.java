package com.tutofinder.app.service;

import com.tutofinder.app.dto.CursoDto;
import com.tutofinder.app.dto.create.CreateCursoDto;
import com.tutofinder.app.entity.Curso;
import com.tutofinder.app.entity.Tarjeta;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.repository.CursoRepository;
import com.tutofinder.app.services.impl.CursoServiceImpl;
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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class CursoServiceTest {
    private static final Long CURSO_ID = 1L;
    private static final String NOMBRE_CURSO = "JAVA-ADVANCE";

    public static Curso CURSO = new Curso();

    CreateCursoDto CREATE_CURSO_DTO = new CreateCursoDto();
    private static final String CURSO_DELETED = "CURSO_DELETED";
    private static final Optional<Curso> OPTIONAL_CURSO_EMPTY = Optional.empty();
    private static final Optional<Curso> OPTIONAL_CURSO = Optional.of(CURSO);

    @Mock
    CursoRepository cursoRepository;

    @InjectMocks
    CursoServiceImpl cursoServiceImpl;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        CURSO.setId(CURSO_ID);
        CURSO.setNombre(NOMBRE_CURSO);

        CREATE_CURSO_DTO.setNombre(NOMBRE_CURSO);

    }

    @Test
    public void createCursoTest() throws BookingException {
        Mockito.when(cursoRepository.findById(CURSO_ID)).thenReturn(OPTIONAL_CURSO);
        Mockito.when(cursoRepository.save(Mockito.any(Curso.class))).thenReturn(CURSO);
        cursoServiceImpl.createCurso(CREATE_CURSO_DTO);
    }

    @Test
    public void getCursoByIdTest() throws BookingException {
        Mockito.when(cursoRepository.findById(CURSO_ID)).thenReturn(OPTIONAL_CURSO);
        cursoServiceImpl.getCursoById(CURSO_ID);
    }

    @Test
    public void getCursosTest() throws BookingException {
        final Curso curso = new Curso();
        Mockito.when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso));
        final List<CursoDto> response = cursoServiceImpl.getCursos();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test(expected = MockitoException.class)
    public void createTarjetaInternalServerErrorTest() throws BookingException {
        Mockito.when(cursoRepository.findById(CURSO_ID)).thenReturn(OPTIONAL_CURSO);
        Mockito.doThrow(Exception.class).when(cursoRepository).save(Mockito.any(Curso.class));
        cursoServiceImpl.createCurso(CREATE_CURSO_DTO);
        fail();
    }

    @Test
    public void deleteTarjetaOk() throws BookingException {
        Mockito.when(cursoRepository.findById(CURSO_ID)).thenReturn(OPTIONAL_CURSO);
        final String response = cursoServiceImpl.deleteCurso(CURSO_ID);
        assertEquals(response, CURSO_DELETED);
    }

    @Test(expected = BookingException.class)
    public void deleteTarjetaNotFountError() throws BookingException {
        Mockito.when(cursoRepository.findById(CURSO_ID)).thenReturn(OPTIONAL_CURSO_EMPTY);
        final String response = cursoServiceImpl.deleteCurso(CURSO_ID);
        assertEquals(response, CURSO_DELETED);
        fail();
    }

    @Test(expected = MockitoException.class)
    public void deleteTarjetaInternalServerError() throws BookingException {
        Mockito.when(cursoRepository.findById(CURSO_ID)).thenReturn(OPTIONAL_CURSO);
        Mockito.doThrow(Exception.class).when(cursoRepository).deleteById(CURSO_ID);
        final String response = cursoServiceImpl.deleteCurso(CURSO_ID);
        assertEquals(response, CURSO_DELETED);
        fail();
    }


}











