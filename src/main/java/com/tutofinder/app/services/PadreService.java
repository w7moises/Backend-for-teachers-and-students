package com.tutofinder.app.services;

import com.tutofinder.app.dto.PadreDto;
import com.tutofinder.app.dto.create.CreatePadreDto;
import com.tutofinder.app.exception.BookingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PadreService {
    PadreDto getPadreById(Long padreId) throws BookingException;

    List<PadreDto> getPadres() throws BookingException;

    PadreDto createPadre(CreatePadreDto createPadreDto, MultipartFile archivo) throws BookingException, IOException;

    PadreDto updatePadre(CreatePadreDto createPadreDto, Long padreId, MultipartFile archivo) throws BookingException, IOException;

    String deletePadre(Long padreId) throws BookingException;
}
