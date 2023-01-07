package com.tutofinder.app.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tutofinder.app.dto.AlumnoDto;
import com.tutofinder.app.dto.create.CreateAlumnoDto;
import com.tutofinder.app.entity.Alumno;
import com.tutofinder.app.entity.Padre;
import com.tutofinder.app.entity.Tutoria;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.repository.AlumnoRepository;
import com.tutofinder.app.repository.PadreRepository;
import com.tutofinder.app.services.impl.AlumnoServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

public class AlumnoServiceTest {
    private static final Long ALUMNO_ID = 1L;
    private static final String NOMBRE_ALUMNO = "RASO";
    private static final String ALUMNO_APELLIDO = "RES";
    private static final String ALUMNO_DNI = "AADADD";
    private static final String ALUMNO_GRADO_DE_ESTUDIO = "SUP";
    private static final long PADRE_ID = 1L;
    private static final String ALUMNO_CORREO = "SDADADDDADA";
    private static final byte[] ALUMNO_FOTO = "Any String you want".getBytes();

    public static final Alumno ALUMNO = new Alumno();
    public static final Padre PADRE = new Padre();
    public static final CreateAlumnoDto CREATE_ALUMNO_DTO = new CreateAlumnoDto();

    private static final Optional<Alumno> OPTIONAL_ALUMNO = Optional.of(ALUMNO);
    private static final Optional<Padre> OPTIONAL_PADRE = Optional.of(PADRE);
    public static final List<Tutoria> TUTORIA_DTO_LIST = new ArrayList<>();

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

    @InjectMocks
    AlumnoServiceImpl alumnoServiceImpl;

    @Mock
    PadreRepository padreRepository;

    @Mock
    AlumnoRepository alumnoRepository;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);
        ALUMNO.setId(ALUMNO_ID);
        ALUMNO.setNombre(NOMBRE_ALUMNO);
        ALUMNO.setApellido(ALUMNO_APELLIDO);
        ALUMNO.setCorreo(ALUMNO_CORREO);
        ALUMNO.setDni(ALUMNO_DNI);
        ALUMNO.setFoto(ALUMNO_FOTO);
        ALUMNO.setGradoEstudio(ALUMNO_GRADO_DE_ESTUDIO);

        CREATE_ALUMNO_DTO.setPadreId(PADRE_ID);
        CREATE_ALUMNO_DTO.setNombre(NOMBRE_ALUMNO);
        CREATE_ALUMNO_DTO.setApellido(ALUMNO_APELLIDO);
        CREATE_ALUMNO_DTO.setCorreo(ALUMNO_CORREO);
        CREATE_ALUMNO_DTO.setDni(ALUMNO_DNI);
        CREATE_ALUMNO_DTO.setFoto(ALUMNO_FOTO);
        CREATE_ALUMNO_DTO.setGradoEstudio(ALUMNO_GRADO_DE_ESTUDIO);
    }

    @Test
    public void getPadreByIdTest() throws BookingException {
        Mockito.when(alumnoRepository.findById(ALUMNO_ID)).thenReturn(OPTIONAL_ALUMNO);
        alumnoServiceImpl.getAlumnoById(ALUMNO_ID);
    }

    @Test
    public void getAlumnosTest() throws BookingException {
        final Alumno alumno = new Alumno();
        Mockito.when(alumnoRepository.findAll()).thenReturn(Arrays.asList(alumno));
        final List<AlumnoDto> response = alumnoServiceImpl.getAlumnos();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test
    public void createAlumnoTest() throws BookingException, IOException {
        Mockito.when(alumnoRepository.findById(ALUMNO_ID)).thenReturn(OPTIONAL_ALUMNO);
        Mockito.when(padreRepository.findById(PADRE_ID)).thenReturn(OPTIONAL_PADRE);
        Mockito.when(alumnoRepository.save(Mockito.any(Alumno.class))).thenReturn(ALUMNO);
        alumnoServiceImpl.createAlumno(CREATE_ALUMNO_DTO, FILE);
    }

    @Test
    public void updateAlumnoTest() throws BookingException, IOException {
        Mockito.when(alumnoRepository.findById(ALUMNO_ID)).thenReturn(OPTIONAL_ALUMNO);
        Mockito.when(padreRepository.findById(PADRE_ID)).thenReturn(OPTIONAL_PADRE);
        Mockito.when(alumnoRepository.save(Mockito.any(Alumno.class))).thenReturn(ALUMNO);
        alumnoServiceImpl.updateAlumno(CREATE_ALUMNO_DTO, ALUMNO_ID, FILE);
    }

    @Test
    public void deleteAlumnoTest() throws BookingException {
        Mockito.when(alumnoRepository.findById(ALUMNO_ID)).thenReturn(OPTIONAL_ALUMNO);
        alumnoServiceImpl.deleteAlumno(ALUMNO_ID);
    }
}
