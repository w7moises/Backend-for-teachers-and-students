package com.tutofinder.app.service;

import com.tutofinder.app.dto.FavoritoDto;
import com.tutofinder.app.dto.create.CreateFavoritoDto;
import com.tutofinder.app.entity.Docente;
import com.tutofinder.app.entity.Favorito;
import com.tutofinder.app.entity.Padre;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.repository.FavoritoRepository;
import com.tutofinder.app.services.impl.FavoritoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FavoritoServiceTest {
    private static final Long FAVORITO_ID = 1L;
    private static final Long PADRE_ID = 1L;
    private static final Long DOCENTE_ID = 1L;

    public static final Favorito FAVORITO = new Favorito();

    CreateFavoritoDto CREATE_FAVORITO_DTO = new CreateFavoritoDto();

    private static final String FAVORITO_DELETED = "FAVORITO_DELETED";
    private static final Optional<Favorito> OPTIONAL_FAVORITO_EMPTY = Optional.empty();
    private static final Optional<Favorito> OPTIONAL_FAVORITO = Optional.of(FAVORITO);

    @Mock
    FavoritoRepository favoritoRepository;

    @InjectMocks
    FavoritoServiceImpl favoritoServiceImpl;


    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);
        Docente Docente = new Docente();
        Padre padre = new Padre();
        FAVORITO.setId(FAVORITO_ID);
        FAVORITO.setDocente(Docente);
        FAVORITO.setPadre(padre);

        CREATE_FAVORITO_DTO.setDocenteId(Docente.getId());
        CREATE_FAVORITO_DTO.setPadreId(padre.getId());
    }

    @Test
    public void getFavoritoByIdTest() throws BookingException {
        Mockito.when(favoritoRepository.findById(FAVORITO_ID)).thenReturn(OPTIONAL_FAVORITO);
        favoritoServiceImpl.getFavoritoById(FAVORITO_ID);
    }

    @Test
    public void getFavoritoTest() throws BookingException {
        final Favorito favorito = new Favorito();
        Mockito.when(favoritoRepository.findAll()).thenReturn(Arrays.asList(favorito));
        final List<FavoritoDto> response = favoritoServiceImpl.getFavoritos();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test(expected = BookingException.class)
    public void getFavoritoByIdTestError() throws BookingException {
        Mockito.when(favoritoRepository.findById(FAVORITO_ID)).thenReturn(OPTIONAL_FAVORITO_EMPTY);
        favoritoServiceImpl.getFavoritoById(FAVORITO_ID);
        fail();
    }

    @Test
    public void createFavoritoTest() throws BookingException {
        Mockito.when(favoritoRepository.findById(FAVORITO_ID)).thenReturn(OPTIONAL_FAVORITO);
        Mockito.when(favoritoRepository.save(Mockito.any(Favorito.class))).thenReturn(FAVORITO);
        favoritoServiceImpl.createFavorito(CREATE_FAVORITO_DTO);
    }

    @Test(expected = MockitoException.class)
    public void createFavoritoInternalServerErrorTest() throws BookingException {
        Mockito.when(favoritoRepository.findById(FAVORITO_ID)).thenReturn(OPTIONAL_FAVORITO);
        Mockito.doThrow(Exception.class).when(favoritoRepository).save(Mockito.any(Favorito.class));
        favoritoServiceImpl.createFavorito(CREATE_FAVORITO_DTO);
        fail();
    }

    @Test
    public void deleteFavoritoOk() throws BookingException {
        Mockito.when(favoritoRepository.findById(FAVORITO_ID)).thenReturn(OPTIONAL_FAVORITO);
        final String response = favoritoServiceImpl.deleteFavorito(FAVORITO_ID);
        assertEquals(response, FAVORITO_DELETED);
    }

    @Test(expected = BookingException.class)
    public void deleteFavoritoNotFountError() throws BookingException {
        Mockito.when(favoritoRepository.findById(FAVORITO_ID)).thenReturn(OPTIONAL_FAVORITO_EMPTY);
        final String response = favoritoServiceImpl.deleteFavorito(FAVORITO_ID);
        assertEquals(response, FAVORITO_DELETED);
        fail();
    }

    @Test(expected = MockitoException.class)
    public void deleteFavoritoInternalServerError() throws BookingException {
        Mockito.when(favoritoRepository.findById(FAVORITO_ID)).thenReturn(OPTIONAL_FAVORITO);
        Mockito.doThrow(Exception.class).when(favoritoRepository).deleteById(FAVORITO_ID);
        final String response = favoritoServiceImpl.deleteFavorito(FAVORITO_ID);
        assertEquals(response, FAVORITO_DELETED);
        fail();
    }


}
