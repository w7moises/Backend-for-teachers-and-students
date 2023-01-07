package com.tutofinder.app.controller;

import com.tutofinder.app.dto.InformeDto;
import com.tutofinder.app.dto.create.CreateInformeDto;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.response.BookingResponse;
import com.tutofinder.app.services.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@RestController
@RequestMapping(path = "/tutofinder")
public class InformeController {
    @Autowired
    InformeService informeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/informes/{informeId}")
    public BookingResponse<InformeDto> getInformeById(@PathVariable Long informeId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                informeService.getInformeById(informeId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/informes")
    public BookingResponse<List<InformeDto>> getInformes() throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                informeService.getInformes());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/informes")
    public BookingResponse<InformeDto> createInforme(@RequestBody @Valid CreateInformeDto createInformeDto) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                informeService.createInforme(createInformeDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/informes/{informeId}")
    public BookingResponse<InformeDto> updateInforme(@PathVariable Long informeId, @RequestBody @Valid CreateInformeDto createInformeDto) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                informeService.updateInforme(createInformeDto, informeId));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/informes/{informeId}")
    public BookingResponse<String> deleteInforme(@PathVariable Long informeId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                informeService.deleteInforme(informeId));
    }
}
