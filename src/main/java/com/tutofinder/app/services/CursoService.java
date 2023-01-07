package com.tutofinder.app.services;

import com.tutofinder.app.dto.CursoDto;
import com.tutofinder.app.dto.create.CreateCursoDto;
import com.tutofinder.app.exception.BookingException;

import java.util.List;

public interface CursoService {
    CursoDto getCursoById(Long cursoId) throws BookingException;

    CursoDto getCursoByNombre(String nombre) throws BookingException;

    List<CursoDto> getCursos() throws BookingException;

    CursoDto createCurso(CreateCursoDto createCursoDto) throws BookingException;

    CursoDto updateCurso(CreateCursoDto createCursoDto, Long cursoId) throws BookingException;

    String deleteCurso(Long cursoId) throws BookingException;
}
