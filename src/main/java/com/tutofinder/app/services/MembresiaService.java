package com.tutofinder.app.services;

import com.tutofinder.app.dto.MembresiaDto;
import com.tutofinder.app.dto.create.CreateMembresiaDto;
import com.tutofinder.app.exception.BookingException;

import java.util.List;

public interface MembresiaService {
    MembresiaDto getMembresiaById(Long membresiaId) throws BookingException;

    List<MembresiaDto> getMembresias() throws BookingException;

    MembresiaDto createMembresia(CreateMembresiaDto createMembresiaDto) throws BookingException;

    MembresiaDto updateMembresia(CreateMembresiaDto createMembresiaDto, Long membresiaId) throws BookingException;

    String deleteMembresia(Long membresiaId) throws BookingException;
}
