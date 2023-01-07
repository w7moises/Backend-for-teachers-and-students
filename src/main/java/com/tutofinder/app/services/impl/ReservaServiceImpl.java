package com.tutofinder.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.tutofinder.app.dto.ReservaDto;
import com.tutofinder.app.dto.create.CreateReservaDto;
import com.tutofinder.app.entity.Alumno;
import com.tutofinder.app.entity.Reserva;
import com.tutofinder.app.entity.Tutoria;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.exception.InternalServerErrorException;
import com.tutofinder.app.exception.NotFoundException;
import com.tutofinder.app.repository.AlumnoRepository;
import com.tutofinder.app.repository.ReservaRepository;
import com.tutofinder.app.repository.TutoriaRepository;
import com.tutofinder.app.services.ReservaService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    TutoriaRepository tutoriaRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ReservaDto getReservaById(Long reservaId) throws BookingException {
        return modelMapper.map(getReservaEntity(reservaId), ReservaDto.class);
    }

    @Override
    public List<ReservaDto> getReservas() throws BookingException {
        List<Reserva> reservaEntity = reservaRepository.findAll();
        return reservaEntity.stream().map(service -> modelMapper.map(service, ReservaDto.class)).collect(Collectors.toList());
    }

    @Override
    public ReservaDto createReserva(CreateReservaDto createReservaDto) throws BookingException {
        final Tutoria tutoria = tutoriaRepository.findById(createReservaDto.getTutoriaId()).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "TUTORIA_NOT_FOUND"));
        final Alumno alumno = alumnoRepository.findById(createReservaDto.getAlumnoId()).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "ALUMNO_NOT_FOUND"));
        Reserva reservaEntity = new Reserva();
        Long id;
        reservaEntity.setAlumno(alumno);
        reservaEntity.setTutoria(tutoria);
        try {
            id = reservaRepository.save(reservaEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getReservaEntity(id), ReservaDto.class);
    }

    @Override
    public String deleteReserva(Long reservaId) throws BookingException {
        reservaRepository.findById(reservaId)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "PAGO_NOT_FOUND"));
        try {
            reservaRepository.deleteById(reservaId);
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return "RESERVA_DELETED";
    }


    private Reserva getReservaEntity(Long reservaId) throws NotFoundException {
        return reservaRepository.findById(reservaId)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESERVA_NOT_FOUND"));
    }

}
