package com.tutofinder.app.services.impl;

import com.tutofinder.app.dto.DocenteDto;
import com.tutofinder.app.dto.create.CreateDocenteDto;
import com.tutofinder.app.entity.Docente;
import com.tutofinder.app.entity.Membresia;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.exception.InternalServerErrorException;
import com.tutofinder.app.exception.NotFoundException;
import com.tutofinder.app.repository.DocenteRepository;
import com.tutofinder.app.repository.MembresiaRepository;
import com.tutofinder.app.services.DocenteService;
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
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    MembresiaRepository membresiaRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public DocenteDto getDocenteById(Long docenteId) throws BookingException {
        Optional<Membresia> membresiaEntity = membresiaRepository.findByDocenteId(docenteId);
        Optional<Docente> docente = docenteRepository.findById(docenteId);
        Docente docenteEntity = docente.get();
        if (membresiaEntity.isPresent()) {
            docenteEntity.setMembresia(true);
        }
        if (!membresiaEntity.isPresent()) {
            docenteEntity.setMembresia(false);
        }
        docenteRepository.save(docenteEntity);
        return modelMapper.map(getDocenteEntity(docenteId), DocenteDto.class);
    }

    @Override
    @Transactional
    public List<DocenteDto> getDocentes() throws BookingException {
        final List<Docente> docenteEntity = docenteRepository.findAll();
        docenteEntity.forEach(
                docente -> {
                    Optional<Membresia> membresiaEntity = membresiaRepository.findByDocenteId(docente.getId());
                    if (membresiaEntity.isPresent()) {
                        docente.setMembresia(true);
                    }
                    if (!membresiaEntity.isPresent()) {
                        docente.setMembresia(false);
                    }
                    docenteRepository.save(docente);
                }
        );
        return docenteEntity.stream().map(
                service -> modelMapper.map(service, DocenteDto.class)).collect(Collectors.toList());
    }

    @Override
    public DocenteDto createDocente(CreateDocenteDto createDocenteDto, MultipartFile archivo) throws BookingException, IOException {
        Docente docenteEntity = new Docente();
        Long id;
        docenteEntity.setNombre(createDocenteDto.getNombre());
        docenteEntity.setApellido(createDocenteDto.getApellido());
        docenteEntity.setDni(createDocenteDto.getDni());
        docenteEntity.setMembresia(false);
        docenteEntity.setCostoHora(createDocenteDto.getCostoHora());
        docenteEntity.setFoto(archivo.getBytes());
        docenteEntity.setDomicilio(createDocenteDto.getDomicilio());
        docenteEntity.setCorreo(createDocenteDto.getCorreo());
        docenteEntity.setNumeroCuenta(createDocenteDto.getNumeroCuenta());
        try {
            id = docenteRepository.save(docenteEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getDocenteEntity(id), DocenteDto.class);
    }

    @Override
    public DocenteDto updateDocente(CreateDocenteDto createDocenteDto, Long docenteId, MultipartFile archivo) throws BookingException, IOException {
        Optional<Docente> docente = docenteRepository.findById(docenteId);
        if (!docente.isPresent()) {
            throw new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND");
        }
        Docente docenteEntity = docente.get();
        Long id;
        docenteEntity.setNombre(createDocenteDto.getNombre());
        docenteEntity.setApellido(createDocenteDto.getApellido());
        docenteEntity.setDni(createDocenteDto.getDni());
        docenteEntity.setFoto(archivo.getBytes());
        docenteEntity.setCostoHora(createDocenteDto.getCostoHora());
        docenteEntity.setDomicilio(createDocenteDto.getDomicilio());
        docenteEntity.setCorreo(createDocenteDto.getCorreo());
        docenteEntity.setNumeroCuenta(createDocenteDto.getNumeroCuenta());
        try {
            id = docenteRepository.save(docenteEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getDocenteEntity(id), DocenteDto.class);
    }

    @Override
    public String deleteDocente(Long docenteId) throws BookingException {
        docenteRepository.findById(docenteId).
                orElseThrow(() -> new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND"));
        try {
            docenteRepository.deleteById(docenteId);
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return "DOCENTE_DELETED";
    }

    private Docente getDocenteEntity(Long docenteId) throws BookingException {
        return docenteRepository.findById(docenteId).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "DOCENTE_NOT_FOUND"));
    }
}