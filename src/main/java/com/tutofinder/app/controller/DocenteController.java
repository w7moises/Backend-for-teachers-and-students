package com.tutofinder.app.controller;

import com.tutofinder.app.dto.DocenteDto;
import com.tutofinder.app.dto.create.CreateDocenteDto;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.response.BookingResponse;
import com.tutofinder.app.services.DocenteService;
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
public class DocenteController {
    @Autowired
    DocenteService docenteService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/docentes/{docenteId}")
    public BookingResponse<DocenteDto> getDocenteById(@PathVariable Long docenteId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                docenteService.getDocenteById(docenteId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/docentes/{docenteId}/img")
    public ResponseEntity<?> getFoto(@PathVariable Long docenteId) throws BookingException {
        DocenteDto optionalDocente = docenteService.getDocenteById(docenteId);
        Resource imagen = new ByteArrayResource(optionalDocente.getFoto());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/docentes")
    public BookingResponse<List<DocenteDto>> getDocentes() throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                docenteService.getDocentes());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/docentes")
    public BookingResponse<DocenteDto> createDocente(@Valid CreateDocenteDto createDocenteDto, @RequestParam MultipartFile archivo) throws BookingException, IOException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                docenteService.createDocente(createDocenteDto, archivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/docentes/{docenteId}")
    public BookingResponse<DocenteDto> updateDocente(@Valid CreateDocenteDto createDocenteDto, @PathVariable Long docenteId, @RequestParam MultipartFile archivo) throws BookingException, IOException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                docenteService.updateDocente(createDocenteDto, docenteId, archivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/docentes/{docenteId}")
    public BookingResponse<String> deleteDocente(@PathVariable Long docenteId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                docenteService.deleteDocente(docenteId));
    }
}
