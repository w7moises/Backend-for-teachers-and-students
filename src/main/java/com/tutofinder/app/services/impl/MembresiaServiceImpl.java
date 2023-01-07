package com.tutofinder.app.services.impl;

import com.tutofinder.app.dto.MembresiaDto;
import com.tutofinder.app.dto.create.CreateMembresiaDto;
import com.tutofinder.app.entity.Docente;
import com.tutofinder.app.entity.Membresia;
import com.tutofinder.app.entity.Tarjeta;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.exception.InternalServerErrorException;
import com.tutofinder.app.exception.NotFoundException;
import com.tutofinder.app.repository.DocenteRepository;
import com.tutofinder.app.repository.MembresiaRepository;
import com.tutofinder.app.repository.TarjetaRepository;
import com.tutofinder.app.services.MembresiaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MembresiaServiceImpl implements MembresiaService {

    @Autowired
    MembresiaRepository membresiaRepository;

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    TarjetaRepository tarjetaRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public MembresiaDto getMembresiaById(Long membresiaId) throws BookingException {
        return modelMapper.map(getMembresiaEntity(membresiaId), MembresiaDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MembresiaDto> getMembresias() throws BookingException {
        List<Membresia> membresiaEntity = membresiaRepository.findAll();
        return membresiaEntity.stream().map(service -> modelMapper.map(service, MembresiaDto.class)).collect(Collectors.toList());
    }

    @Override
    public MembresiaDto createMembresia(CreateMembresiaDto createMembresiaDto) throws BookingException {
        final Docente docente = docenteRepository.findById(createMembresiaDto.getDocenteId())
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "DOCENTE_NOT_FOUND"));
        final Tarjeta tarjeta = tarjetaRepository.findById(createMembresiaDto.getTarjetaId())
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "TARJETA_NOT_FOUND"));

        if (membresiaRepository.findByDocenteIdAndTarjetaId(docente.getId(), tarjeta.getId()).isPresent()) {
            throw new NotFoundException("MEMBRESIA_EXIST", "MEMBRESIA_EXIST");
        }
        Membresia membresiaEntity = new Membresia();
        Long id;
        membresiaEntity.setDescripcionMembresia(createMembresiaDto.getDescripcionMembresia());
        membresiaEntity.setFechaExpiracion(createMembresiaDto.getFechaExpiracion());
        membresiaEntity.setTarjeta(tarjeta);
        membresiaEntity.setDocente(docente);
        membresiaEntity.setCostoMembresia(createMembresiaDto.getCostoMembresia());

        try {
            id = membresiaRepository.save(membresiaEntity).getId();
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_ERROR", "INTERNAL_ERROR");
        }
        return modelMapper.map(getMembresiaEntity(id), MembresiaDto.class);
    }

    @Override
    public MembresiaDto updateMembresia(CreateMembresiaDto createMembresiaDto, Long membresiaId) throws BookingException {
        final Docente docente = docenteRepository.findById(createMembresiaDto.getDocenteId())
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "DOCENTE_NOT_FOUND"));
        final Tarjeta tarjeta = tarjetaRepository.findById(createMembresiaDto.getTarjetaId())
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "TARJETA_NOT_FOUND"));
        if (membresiaRepository.findByDocenteIdAndTarjetaId(docente.getId(), tarjeta.getId()).isPresent()) {
            throw new NotFoundException("MEMBRESIA_EXIST", "MEMBRESIA_EXIST");
        }
        Optional<Membresia> membresia = membresiaRepository.findById(membresiaId);
        if (!membresia.isPresent()) {
            throw new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND");
        }
        Membresia membresiaEntity = membresia.get();
        Long id;
        membresiaEntity.setDescripcionMembresia(createMembresiaDto.getDescripcionMembresia());
        membresiaEntity.setFechaExpiracion(createMembresiaDto.getFechaExpiracion());
        membresiaEntity.setTarjeta(tarjeta);
        membresiaEntity.setDocente(docente);
        membresiaEntity.setCostoMembresia(createMembresiaDto.getCostoMembresia());

        try {
            id = membresiaRepository.save(membresiaEntity).getId();
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_ERROR", "INTERNAL_ERROR");
        }
        return modelMapper.map(getMembresiaEntity(id), MembresiaDto.class);
    }

    @Override
    public String deleteMembresia(Long membresiaId) throws BookingException {
        membresiaRepository.findById(membresiaId)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "MEMBRESIA_NOT_FOUND"));
        try {
            membresiaRepository.deleteById(membresiaId);
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return "INFORME_DELETED";
    }

    private Membresia getMembresiaEntity(Long membresiaId) throws BookingException {
        return membresiaRepository.findById(membresiaId)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "MEMBRESIA_NOT_FOUND"));
    }
}
