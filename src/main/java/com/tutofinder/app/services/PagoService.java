package com.tutofinder.app.services;

import com.tutofinder.app.dto.PagoDto;
import com.tutofinder.app.dto.create.CreatePagoDto;
import com.tutofinder.app.exception.BookingException;

import java.util.List;

public interface PagoService {
    PagoDto getPagoById(Long pagoId) throws BookingException;

    List<PagoDto> getPagos() throws BookingException;

    PagoDto createPago(CreatePagoDto createPagoDto) throws BookingException;

    PagoDto updatePago(CreatePagoDto createPagoDto, Long pagoId) throws BookingException;

    String deletePago(Long pagoId) throws BookingException;
}
