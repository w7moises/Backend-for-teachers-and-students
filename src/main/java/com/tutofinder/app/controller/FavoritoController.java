package com.tutofinder.app.controller;

import com.tutofinder.app.dto.FavoritoDto;
import com.tutofinder.app.dto.create.CreateFavoritoDto;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.response.BookingResponse;
import com.tutofinder.app.services.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@RestController
@RequestMapping(path = "/tutofinder")
public class FavoritoController {

    @Autowired
    FavoritoService favoritoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/favoritos/{favoritoId}")
    public BookingResponse<FavoritoDto> getFavoritoById(@PathVariable Long favoritoId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                favoritoService.getFavoritoById(favoritoId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/favoritos/{padreId}")
    public BookingResponse<List<FavoritoDto>> getFavoritoByPadreId(@PathVariable Long padreId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                favoritoService.getFavoritoByPadreId(padreId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/favoritos")
    public BookingResponse<List<FavoritoDto>> getFavoritos() throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                favoritoService.getFavoritos());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/favoritos/pagina")
    public BookingResponse<?> getFavoritos(Pageable pageable) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                favoritoService.getFavoritos(pageable));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/favoritos")
    public BookingResponse<FavoritoDto> createFavorito(@RequestBody @Valid CreateFavoritoDto createFavoritoDto) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                favoritoService.createFavorito(createFavoritoDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/favoritos/{favoritoId}")
    public BookingResponse<FavoritoDto> updateFavorito(@PathVariable Long favoritoId, @RequestBody @Valid CreateFavoritoDto createFavoritoDto) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                favoritoService.updateFavorito(createFavoritoDto, favoritoId));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/favoritos/{favoritoId}")
    public BookingResponse<String> deleteFavorito(@PathVariable Long favoritoId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                favoritoService.deleteFavorito(favoritoId));
    }

}
