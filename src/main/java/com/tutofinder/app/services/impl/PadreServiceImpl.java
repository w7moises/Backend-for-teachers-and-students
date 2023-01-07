package com.tutofinder.app.services.impl;

import com.tutofinder.app.dto.PadreDto;
import com.tutofinder.app.dto.create.CreatePadreDto;
import com.tutofinder.app.entity.Padre;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.exception.InternalServerErrorException;
import com.tutofinder.app.exception.NotFoundException;
import com.tutofinder.app.repository.PadreRepository;
import com.tutofinder.app.services.PadreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PadreServiceImpl implements PadreService {
    @Autowired
    PadreRepository padreRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public PadreDto getPadreById(Long padreId) throws BookingException {
        return modelMapper.map(getPadreEntity(padreId), PadreDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PadreDto> getPadres() throws BookingException {
        final List<Padre> padreEntity = padreRepository.findAll();
        return padreEntity.stream().map(service -> modelMapper.map(service, PadreDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PadreDto createPadre(CreatePadreDto createPadreDto, MultipartFile archivo) throws BookingException, IOException {
        Padre padreEntity = new Padre();
        Long id;
        padreEntity.setNombre(createPadreDto.getNombre());
        padreEntity.setApellido(createPadreDto.getApellido());
        padreEntity.setFoto(archivo.getBytes());
        padreEntity.setDni(createPadreDto.getDni());
        padreEntity.setCorreo(createPadreDto.getCorreo());
        try {
            id = padreRepository.save(padreEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getPadreEntity(id), PadreDto.class);
    }

    @Override
    @Transactional
    public PadreDto updatePadre(CreatePadreDto createPadreDto, Long padreId, MultipartFile archivo) throws BookingException, IOException {
        Optional<Padre> padre = padreRepository.findById(padreId);
        if (!padre.isPresent()) {
            throw new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND");
        }
        Padre padreEntity = padre.get();
        Long id;
        padreEntity.setNombre(createPadreDto.getNombre());
        padreEntity.setApellido(createPadreDto.getApellido());
        padreEntity.setFoto(archivo.getBytes());
        padreEntity.setDni(createPadreDto.getDni());
        padreEntity.setCorreo(createPadreDto.getCorreo());
        try {
            id = padreRepository.save(padreEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getPadreEntity(id), PadreDto.class);
    }

    @Override
    public String deletePadre(Long padreId) throws BookingException {
        padreRepository.findById(padreId).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESTAURANT_NOT_FOUND"));
        try {
            padreRepository.deleteById(padreId);
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return "PADRE_DELETED";
    }

    private Padre getPadreEntity(Long padreId) throws BookingException {
        return padreRepository.findById(padreId).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESTAURANT_NOT_FOUND"));
    }
}
