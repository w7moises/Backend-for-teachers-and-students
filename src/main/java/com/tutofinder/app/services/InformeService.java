package com.tutofinder.app.services;

import com.tutofinder.app.dto.InformeDto;
import com.tutofinder.app.dto.create.CreateInformeDto;
import com.tutofinder.app.exception.BookingException;

import java.util.List;

public interface InformeService {
    InformeDto getInformeById(Long informeId) throws BookingException;

    List<InformeDto> getInformes() throws BookingException;

    InformeDto createInforme(CreateInformeDto createInformeDto) throws BookingException;

    InformeDto updateInforme(CreateInformeDto createInformeDto, Long informeId) throws BookingException;

    String deleteInforme(Long informeId) throws BookingException;
}
