package com.tutofinder.app.services;

import com.tutofinder.app.dto.TutoriaDto;
import com.tutofinder.app.dto.create.CreateTutoriaDto;
import com.tutofinder.app.exception.BookingException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TutoriaService {
    TutoriaDto getTutoriaById(Long tutoriaId) throws BookingException;

    List<TutoriaDto> getTutorias() throws BookingException;

    List<TutoriaDto> getTutorias(Pageable pageable) throws BookingException;

    TutoriaDto createTutoria(CreateTutoriaDto createTutoriaDto) throws BookingException;

    TutoriaDto updateTutoria(CreateTutoriaDto createTutoriaDto, Long tutoriaId) throws BookingException;

    String deleteTutoria(Long tutoriaId) throws BookingException;
}
