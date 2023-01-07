package com.tutofinder.app.services.impl;

import com.tutofinder.app.dto.TarjetaDto;
import com.tutofinder.app.dto.create.CreateTarjetaDto;
import com.tutofinder.app.entity.Tarjeta;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.exception.InternalServerErrorException;
import com.tutofinder.app.exception.NotFoundException;
import com.tutofinder.app.repository.TarjetaRepository;
import com.tutofinder.app.services.TarjetaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarjetaServiceImpl implements TarjetaService {
    @Autowired
    TarjetaRepository tarjetaRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public TarjetaDto getTarjetaById(Long tarjetaId) throws BookingException {
        return modelMapper.map(getTarjetaEntity(tarjetaId), TarjetaDto.class);
    }

    @Override
    public List<TarjetaDto> getTarjetasByNombre(String nombre) throws BookingException {
        final List<Tarjeta> tarjetasEntity = tarjetaRepository.findByNombrePoseedor(nombre);
        return tarjetasEntity.stream().map(service -> modelMapper.map(service, TarjetaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TarjetaDto> getTarjetas() throws BookingException {
        final List<Tarjeta> tarjetasEntity = tarjetaRepository.findAll();
        return tarjetasEntity.stream().map(service -> modelMapper.map(service, TarjetaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TarjetaDto createTarjeta(CreateTarjetaDto createTarjetaDto) throws BookingException {
        Tarjeta tarjetaEntity = new Tarjeta();
        Long id;
        tarjetaEntity.setNombrePoseedor(createTarjetaDto.getNombrePoseedor());
        tarjetaEntity.setNumeroTarjeta(createTarjetaDto.getNumeroTarjeta());
        tarjetaEntity.setFechaExpiracion(createTarjetaDto.getFechaExpiracion());

        try {
            id = tarjetaRepository.save(tarjetaEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getTarjetaEntity(id), TarjetaDto.class);

    }

    @Override
    @Transactional
    public TarjetaDto updateTarjeta(CreateTarjetaDto createTarjetaDto, Long tarjetaId) throws BookingException {
        Optional<Tarjeta> tarjeta = tarjetaRepository.findById(tarjetaId);
        if (!tarjeta.isPresent()) {
            throw new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND");
        }
        Tarjeta tarjetaEntity = tarjeta.get();
        Long id;
        tarjetaEntity.setNombrePoseedor(createTarjetaDto.getNombrePoseedor());
        tarjetaEntity.setNumeroTarjeta(createTarjetaDto.getNumeroTarjeta());
        tarjetaEntity.setFechaExpiracion(createTarjetaDto.getFechaExpiracion());
        try {
            id = tarjetaRepository.save(tarjetaEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getTarjetaEntity(id), TarjetaDto.class);
    }

    @Override
    public String deleteTarjeta(Long tarjetaId) throws BookingException {
        tarjetaRepository.findById(tarjetaId)
                .orElseThrow(() -> new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND"));
        try {
            tarjetaRepository.deleteById(tarjetaId);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_ERROR", "INTERNAL_ERROR");
        }

        return "TARJETA_DELETED";
    }

    private Tarjeta getTarjetaEntity(Long tarjetaId) throws BookingException {
        return tarjetaRepository.findById(tarjetaId)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "TARJETA_NOT_FOUND"));
    }
}
