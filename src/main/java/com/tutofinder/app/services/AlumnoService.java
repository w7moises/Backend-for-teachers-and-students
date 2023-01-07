package com.tutofinder.app.services;

import com.tutofinder.app.dto.AlumnoDto;
import com.tutofinder.app.dto.create.CreateAlumnoDto;
import com.tutofinder.app.exception.BookingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AlumnoService {
    AlumnoDto getAlumnoById(Long alumnoId) throws BookingException;

    List<AlumnoDto> getAlumnos() throws BookingException;

    AlumnoDto createAlumno(CreateAlumnoDto createAlumnoDto, MultipartFile archivo) throws BookingException, IOException;

    AlumnoDto updateAlumno(CreateAlumnoDto createAlumnoDto, Long alumnoId, MultipartFile archivo) throws BookingException, IOException;

    String deleteAlumno(Long alumnoId) throws BookingException;
}
