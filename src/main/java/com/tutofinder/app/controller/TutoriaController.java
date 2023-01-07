package com.tutofinder.app.controller;


import com.tutofinder.app.dto.TutoriaDto;
import com.tutofinder.app.dto.create.CreateTutoriaDto;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.response.BookingResponse;
import com.tutofinder.app.services.TutoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@RestController
@RequestMapping(path = "/tutofinder")
public class TutoriaController {

    @Autowired
    TutoriaService tutoriaService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tutorias/{tutoriaId}")
    public BookingResponse<TutoriaDto> getTutoriaById(@PathVariable Long tutoriaId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                tutoriaService.getTutoriaById(tutoriaId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tutorias")
    public BookingResponse<List<TutoriaDto>> getTutorias() throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                tutoriaService.getTutorias());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tutorias/pagina")
    public BookingResponse<?> getTutorias(Pageable pageable) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                tutoriaService.getTutorias(pageable));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/tutorias")
    public BookingResponse<TutoriaDto> createTutoria(@RequestBody @Valid CreateTutoriaDto createTutoriaDto) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                tutoriaService.createTutoria(createTutoriaDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/tutorias/{tutoriaId}")
    public BookingResponse<TutoriaDto> updateTutoria(@PathVariable Long tutoriaId, @RequestBody @Valid CreateTutoriaDto createTutoriaDto) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                tutoriaService.updateTutoria(createTutoriaDto, tutoriaId));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/tutorias/{tutoriaId}")
    public BookingResponse<String> deleteTutoria(@PathVariable Long tutoriaId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                tutoriaService.deleteTutoria(tutoriaId));
    }
}
