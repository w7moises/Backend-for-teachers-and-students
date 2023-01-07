package com.tutofinder.app.services.impl;

import com.tutofinder.app.dto.FavoritoDto;
import com.tutofinder.app.dto.create.CreateFavoritoDto;
import com.tutofinder.app.entity.Docente;
import com.tutofinder.app.entity.Favorito;
import com.tutofinder.app.entity.Padre;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.exception.InternalServerErrorException;
import com.tutofinder.app.exception.NotFoundException;
import com.tutofinder.app.repository.DocenteRepository;
import com.tutofinder.app.repository.FavoritoRepository;
import com.tutofinder.app.repository.PadreRepository;
import com.tutofinder.app.services.FavoritoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    FavoritoRepository favoritoRepository;

    @Autowired
    PadreRepository padreRepository;

    @Autowired
    DocenteRepository docenteRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public FavoritoDto getFavoritoById(Long favoritoId) throws BookingException {
        return modelMapper.map(getFavoritoEntity(favoritoId), FavoritoDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FavoritoDto> getFavoritos() throws BookingException {
        List<Favorito> favoritoEntity = (List<Favorito>) favoritoRepository.findAll();
        return favoritoEntity.stream().map(service -> modelMapper.map(service, FavoritoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FavoritoDto> getFavoritoByPadreId(Long padreId) throws BookingException {
        List<Favorito> favoritoEntity = favoritoRepository.findFavoritoByPadreId(padreId);
        return favoritoEntity.stream().map(service -> modelMapper.map(service, FavoritoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FavoritoDto> getFavoritos(Pageable pageable) throws BookingException {
        Page<Favorito> favorito = favoritoRepository.findAll(pageable);
        return favorito.stream().map(service -> modelMapper.map(service, FavoritoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FavoritoDto createFavorito(CreateFavoritoDto createFavoritoDto) throws BookingException {
        final Padre padre = padreRepository.findById(createFavoritoDto.getPadreId()).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "PADRE_NOT_FOUND"));

        final Docente docente = docenteRepository.findById(createFavoritoDto.getDocenteId())
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "DOCENTE_NOT_FOUND"));

        Favorito favoritoEntity = new Favorito();
        Long id;
        favoritoEntity.setPadre(padre);
        favoritoEntity.setDocente(docente);

        try {
            id = favoritoRepository.save(favoritoEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getFavoritoEntity(id), FavoritoDto.class);
    }

    @Override
    @Transactional
    public FavoritoDto updateFavorito(CreateFavoritoDto createFavoritoDto, Long favoritoId) throws BookingException {
        final Padre padre = padreRepository.findById(createFavoritoDto.getPadreId()).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "PADRE_NOT_FOUND"));

        final Docente docente = docenteRepository.findById(createFavoritoDto.getDocenteId())
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "DOCENTE_NOT_FOUND"));

        Optional<Favorito> favorito = favoritoRepository.findById(favoritoId);
        if (!favorito.isPresent()) {
            throw new NotFoundException("ID_NOT_FOOUND", "ID_NOT_FOUND");
        }
        Favorito favoritoEntity = favorito.get();
        Long id;
        favoritoEntity.setDocente(docente);
        favoritoEntity.setPadre(padre);

        try {
            id = favoritoRepository.save(favoritoEntity).getId();
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getFavoritoEntity(id), FavoritoDto.class);
    }

    @Override
    public String deleteFavorito(Long favoritoId) throws BookingException {
        favoritoRepository.findById(favoritoId).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "FAVORITO_NOT_FOUND"));
        try {
            favoritoRepository.deleteById(favoritoId);
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return "FAVORITO_DELETED";
    }

    private Favorito getFavoritoEntity(Long favoritoId) throws BookingException {
        return favoritoRepository.findById(favoritoId).
                orElseThrow(() -> new NotFoundException("SNOT-404-1", "FAVORITO_NOT_FOUND"));
    }
}
