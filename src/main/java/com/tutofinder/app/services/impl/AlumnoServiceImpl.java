package com.tutofinder.app.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tutofinder.app.dto.AlumnoDto;
import com.tutofinder.app.dto.create.CreateAlumnoDto;
import com.tutofinder.app.entity.Alumno;
import com.tutofinder.app.entity.Padre;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.exception.InternalServerErrorException;
import com.tutofinder.app.exception.NotFoundException;
import com.tutofinder.app.repository.AlumnoRepository;
import com.tutofinder.app.repository.PadreRepository;
import com.tutofinder.app.services.AlumnoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    PadreRepository padreRepository;

    public static ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public AlumnoDto getAlumnoById(Long alumnoId) throws BookingException {
        return modelMapper.map(getAlumnoEntity(alumnoId), AlumnoDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoDto> getAlumnos() throws BookingException {
        List<Alumno> alumnoEntity = alumnoRepository.findAll();
        return alumnoEntity.stream().map(service -> modelMapper.map(service, AlumnoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AlumnoDto createAlumno(CreateAlumnoDto createAlumnoDto, MultipartFile archivo) throws BookingException, IOException {
        final Padre padre = padreRepository.findById(createAlumnoDto.getPadreId()).
                orElseThrow(() -> new NotFoundException("PADRE_NOT_FOUND", "PADRE_NOT_FOUND"));
        Alumno alumnoEntity = new Alumno();
        Long id;
        alumnoEntity.setApellido(createAlumnoDto.getApellido());
        alumnoEntity.setGradoEstudio(createAlumnoDto.getGradoEstudio());
        alumnoEntity.setNombre(createAlumnoDto.getNombre());
        alumnoEntity.setFoto(archivo.getBytes());
        alumnoEntity.setPadre(padre);
        alumnoEntity.setDni(createAlumnoDto.getDni());
        alumnoEntity.setCorreo(createAlumnoDto.getCorreo());
        try {
            id = alumnoRepository.save(alumnoEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getAlumnoEntity(id), AlumnoDto.class);
    }

    @Override
    @Transactional
    public AlumnoDto updateAlumno(CreateAlumnoDto createAlumnoDto, Long alumnoId, MultipartFile archivo) throws BookingException, IOException {
        final Padre padre = padreRepository.findById(createAlumnoDto.getPadreId()).
                orElseThrow(() -> new NotFoundException("PADRE_NOT_FOUND", "PADRE_NOT_FOUND"));
        Optional<Alumno> alumno = alumnoRepository.findById(alumnoId);
        if (!alumno.isPresent()) {
            throw new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND");
        }
        Alumno alumnoEntity = alumno.get();
        Long id;
        alumnoEntity.setNombre(createAlumnoDto.getNombre());
        alumnoEntity.setApellido(createAlumnoDto.getApellido());
        alumnoEntity.setGradoEstudio(createAlumnoDto.getGradoEstudio());
        alumnoEntity.setFoto(archivo.getBytes());
        alumnoEntity.setPadre(padre);
        alumnoEntity.setDni(createAlumnoDto.getDni());
        alumnoEntity.setCorreo(createAlumnoDto.getCorreo());
        try {
            id = alumnoRepository.save(alumnoEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getAlumnoEntity(id), AlumnoDto.class);
    }

    @Override
    public String deleteAlumno(Long alumnoId) throws BookingException {
        alumnoRepository.findById(alumnoId).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "ALUMNO_NOT_FOUND"));
        try {
            alumnoRepository.deleteById(alumnoId);
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return "ALUMNO_DELETED";
    }

    private Alumno getAlumnoEntity(Long alumnoId) throws BookingException {
        return alumnoRepository.findById(alumnoId).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "CURSO_NOT_FOUND"));
    }
}
