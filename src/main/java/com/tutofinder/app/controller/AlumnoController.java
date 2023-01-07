package com.tutofinder.app.controller;

import com.tutofinder.app.dto.AlumnoDto;
import com.tutofinder.app.dto.create.CreateAlumnoDto;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.response.BookingResponse;
import com.tutofinder.app.services.AlumnoService;
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
@RequestMapping("/tutofinder")
public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/alumnos/{alumnoId}")
    BookingResponse<AlumnoDto> getAlumnoById(@PathVariable Long alumnoId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                alumnoService.getAlumnoById(alumnoId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/alumnos/{alumnoId}/img")
    public ResponseEntity<?> getFoto(@PathVariable Long alumnoId) throws BookingException {
        AlumnoDto optionalAlumno = alumnoService.getAlumnoById(alumnoId);
        Resource imagen = new ByteArrayResource(optionalAlumno.getFoto());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/alumnos")
    BookingResponse<List<AlumnoDto>> getAlumnos() throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                alumnoService.getAlumnos());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/alumnos")
    BookingResponse<AlumnoDto> createAlumno(@Valid CreateAlumnoDto createAlumnoDto, @RequestParam MultipartFile archivo) throws BookingException, IOException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                alumnoService.createAlumno(createAlumnoDto, archivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/alumnos/{alumnoId}")
    BookingResponse<AlumnoDto> updateAlumno(@PathVariable Long alumnoId, @Valid CreateAlumnoDto createAlumnoDto, @RequestParam MultipartFile archivo) throws BookingException, IOException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                alumnoService.updateAlumno(createAlumnoDto, alumnoId, archivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/alumnos/{alumnoId}")
    BookingResponse<String> deleteAlumno(@PathVariable Long alumnoId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                alumnoService.deleteAlumno(alumnoId));
    }
}
