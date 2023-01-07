package com.tutofinder.app.controller;

import com.tutofinder.app.dto.PadreDto;
import com.tutofinder.app.dto.create.CreatePadreDto;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.response.BookingResponse;
import com.tutofinder.app.services.PadreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@RestController
@RequestMapping(path = "/tutofinder")
public class PadreController {
    @Autowired
    PadreService padreService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/padres/{padreId}")
    public BookingResponse<PadreDto> getPadreById(@PathVariable Long padreId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                padreService.getPadreById(padreId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/padres/{padreId}/img")
    public ResponseEntity<?> getFoto(@PathVariable Long padreId) throws BookingException {
        PadreDto optionalPadre = padreService.getPadreById(padreId);
        Resource imagen = new ByteArrayResource(optionalPadre.getFoto());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/padres")
    public BookingResponse<List<PadreDto>> getPadres() throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                padreService.getPadres());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/padres")
    public BookingResponse<PadreDto> createPadre(@Valid CreatePadreDto createPadreDto, @RequestParam MultipartFile archivo) throws BookingException, IOException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                padreService.createPadre(createPadreDto, archivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/padres/{padreId}")
    public BookingResponse<PadreDto> updatePadre(@Valid CreatePadreDto createPadreDto, @PathVariable Long padreId, @RequestParam MultipartFile archivo) throws BookingException, IOException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                padreService.updatePadre(createPadreDto, padreId, archivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/padres/{padreId}")
    public BookingResponse<String> deletePadre(@PathVariable Long padreId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                padreService.deletePadre(padreId));
    }
}
