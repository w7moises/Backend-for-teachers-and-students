package com.tutofinder.app.services;

import com.tutofinder.app.dto.FavoritoDto;
import com.tutofinder.app.dto.create.CreateFavoritoDto;
import com.tutofinder.app.exception.BookingException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FavoritoService {
    FavoritoDto getFavoritoById(Long favoritoId) throws BookingException;

    List<FavoritoDto> getFavoritos() throws BookingException;

    List<FavoritoDto> getFavoritoByPadreId(Long padreId) throws BookingException;

    List<FavoritoDto> getFavoritos(Pageable pageable) throws BookingException;

    FavoritoDto createFavorito(CreateFavoritoDto createFavoritoDto) throws BookingException;

    FavoritoDto updateFavorito(CreateFavoritoDto createFavoritoDto, Long favoritoId) throws BookingException;

    String deleteFavorito(Long favoritoId) throws BookingException;
}
