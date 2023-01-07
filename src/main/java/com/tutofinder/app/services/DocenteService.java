package com.tutofinder.app.services;

import com.tutofinder.app.dto.DocenteDto;
import com.tutofinder.app.dto.create.CreateDocenteDto;
import com.tutofinder.app.exception.BookingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocenteService {
    DocenteDto getDocenteById(Long docenteId) throws BookingException;

    List<DocenteDto> getDocentes() throws BookingException;

    DocenteDto createDocente(CreateDocenteDto createDocenteDto, MultipartFile archivo) throws BookingException, IOException;

    DocenteDto updateDocente(CreateDocenteDto createDocenteDto, Long docenteId, MultipartFile archivo) throws BookingException, IOException;

    String deleteDocente(Long docenteId) throws BookingException;
}
