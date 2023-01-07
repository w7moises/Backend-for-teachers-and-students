package com.tutofinder.app.controller;

import com.tutofinder.app.dto.ReservaDto;
import com.tutofinder.app.dto.create.CreateReservaDto;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.response.BookingResponse;
import com.tutofinder.app.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@RestController
@RequestMapping(value = "/tutofinder")
public class ReservaController {
    @Autowired
    ReservaService reservaService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/reservas/{reservaId}")
    public BookingResponse<ReservaDto> getReservaById(@PathVariable Long reservaId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                reservaService.getReservaById(reservaId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/reservas")
    public BookingResponse<List<ReservaDto>> getReservas() throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                reservaService.getReservas());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reservas")
    public BookingResponse<ReservaDto> createReserva(@RequestBody @Valid CreateReservaDto createreservaDto) throws BookingException, IOException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                reservaService.createReserva(createreservaDto));
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/reservas/{reservaId}")
    public BookingResponse<String> deleteReserva(@PathVariable Long reservaId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                reservaService.deleteReserva(reservaId));
    }
}
