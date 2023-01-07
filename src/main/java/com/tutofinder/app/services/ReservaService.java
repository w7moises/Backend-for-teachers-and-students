package com.tutofinder.app.services;

import com.tutofinder.app.dto.ReservaDto;
import com.tutofinder.app.dto.create.CreateReservaDto;
import com.tutofinder.app.exception.BookingException;

import java.util.List;

public interface ReservaService {
    ReservaDto getReservaById(Long reservaId) throws BookingException;

    List<ReservaDto> getReservas() throws BookingException;

    ReservaDto createReserva(CreateReservaDto createReservaDto) throws BookingException;

    String deleteReserva(Long reservaId) throws BookingException;
}
