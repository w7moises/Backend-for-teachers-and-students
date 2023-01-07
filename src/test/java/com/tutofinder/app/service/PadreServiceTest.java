package com.tutofinder.app.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tutofinder.app.dto.PadreDto;
import com.tutofinder.app.dto.create.CreatePadreDto;
import com.tutofinder.app.entity.Alumno;
import com.tutofinder.app.entity.Padre;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.repository.PadreRepository;
import com.tutofinder.app.services.impl.PadreServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;


public class PadreServiceTest {
    private static final Long PADRE_ID = 1L;
    private static final String NOMBRE_PADRE = "RASO";
    private static final String NOMBRE_APELLIDO = "RES";
    private static final String DNI = "AADADD";
    private static final String CORREO = "SDADADDDADA";
    private static final byte[] FOTO = "Any String you want".getBytes();
    private static final Date DATE = new Date();
    private static final MultipartFile FILE = new MultipartFile() {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getOriginalFilename() {
            return null;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return new byte[0];
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(File file) throws IOException, IllegalStateException {

        }
    };

    CreatePadreDto CREATE_PADRE_DTO = new CreatePadreDto();

    public static final Padre PADRE = new Padre();

    private static final Optional<Padre> OPTIONAL_PADRE_EMPTY = Optional.empty();
    private static final Optional<Padre> OPTIONAL_PADRE = Optional.of(PADRE);
    public static final List<Alumno> ALUMNNO_LIST = new ArrayList<>();

    @Mock
    PadreRepository padreRepository;

    @InjectMocks
    PadreServiceImpl padreServiceImpl;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);
        PADRE.setId(PADRE_ID);
        PADRE.setNombre(NOMBRE_PADRE);
        PADRE.setApellido(NOMBRE_APELLIDO);
        PADRE.setCorreo(CORREO);
        PADRE.setDni(DNI);
        PADRE.setFoto(FOTO);
        PADRE.setAlumnos(ALUMNNO_LIST);
    }

    @Test
    public void getPadreByIdTest() throws BookingException {
        Mockito.when(padreRepository.findById(PADRE_ID)).thenReturn(OPTIONAL_PADRE);
        padreServiceImpl.getPadreById(PADRE_ID);
    }

    @Test
    public void getPadresTest() throws BookingException {
        final Padre padre = new Padre();
        Mockito.when(padreRepository.findAll()).thenReturn(Arrays.asList(padre));
        final List<PadreDto> response = padreServiceImpl.getPadres();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test
    public void createPadreTest() throws BookingException, IOException {
        Mockito.when(padreRepository.findById(PADRE_ID)).thenReturn(OPTIONAL_PADRE);
        Mockito.when(padreRepository.save(Mockito.any(Padre.class))).thenReturn(PADRE);
        padreServiceImpl.createPadre(CREATE_PADRE_DTO, FILE);
    }

    @Test
    public void deletePadreTest() throws BookingException {
        Mockito.when(padreRepository.findById(PADRE_ID)).thenReturn(OPTIONAL_PADRE);
        padreServiceImpl.deletePadre(PADRE_ID);
    }

}
