package com.tutofinder.app.services.impl;

import com.tutofinder.app.dto.CursoDto;
import com.tutofinder.app.dto.create.CreateCursoDto;
import com.tutofinder.app.entity.Curso;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.exception.InternalServerErrorException;
import com.tutofinder.app.exception.NotFoundException;
import com.tutofinder.app.repository.CursoRepository;
import com.tutofinder.app.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public CursoDto getCursoById(Long cursoId) throws BookingException {
        return modelMapper.map(getCursoEntity(cursoId), CursoDto.class);
    }

    @Override
    public CursoDto getCursoByNombre(String nombre) throws BookingException {
        final Curso curso = cursoRepository.findByNombre(nombre)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "NAME_CURSO_NOT_FOUND"));
        return modelMapper.map(curso, CursoDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDto> getCursos() throws BookingException {
        final List<Curso> cursosEntity = cursoRepository.findAll();
        return cursosEntity.stream().map(service -> modelMapper.map(service, CursoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CursoDto createCurso(CreateCursoDto createCursoDto) throws BookingException {
        Curso cursoEntity = new Curso();
        Long id;
        cursoEntity.setNombre(createCursoDto.getNombre());
        try {
            id = cursoRepository.save(cursoEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getCursoEntity(id), CursoDto.class);
    }

    @Override
    public CursoDto updateCurso(CreateCursoDto createCursoDto, Long cursoId) throws BookingException {
        Optional<Curso> curso = cursoRepository.findById(cursoId);
        if (!curso.isPresent()) {
            throw new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND");
        }
        Curso cursoEntity = curso.get();
        Long id;
        cursoEntity.setNombre(createCursoDto.getNombre());
        try {
            id = cursoRepository.save(cursoEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getCursoEntity(id), CursoDto.class);
    }

    @Override
    public String deleteCurso(Long cursoId) throws BookingException {
        cursoRepository.findById(cursoId)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND"));

        try {
            cursoRepository.deleteById(cursoId);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_ERROR", "INTERNAL_ERROR");
        }

        return "CURSO_DELETED";
    }

    private Curso getCursoEntity(Long cursoId) throws BookingException {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "CURSO_NOT_FOUND"));
    }
}
