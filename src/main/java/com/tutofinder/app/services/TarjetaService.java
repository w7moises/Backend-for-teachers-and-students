package com.tutofinder.app.services;

import com.tutofinder.app.dto.TarjetaDto;
import com.tutofinder.app.dto.create.CreateTarjetaDto;
import com.tutofinder.app.exception.BookingException;

import java.util.List;

public interface TarjetaService {
    TarjetaDto getTarjetaById(Long tarjetaId) throws BookingException;

    List<TarjetaDto> getTarjetasByNombre(String nombre) throws BookingException;

    List<TarjetaDto> getTarjetas() throws BookingException;

    TarjetaDto createTarjeta(CreateTarjetaDto createTarjetaDto) throws BookingException;

    TarjetaDto updateTarjeta(CreateTarjetaDto createTarjetaDto, Long tarjetaId) throws BookingException;

    String deleteTarjeta(Long tarjetaId) throws BookingException;
}
