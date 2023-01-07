package com.tutofinder.app.controller;

import com.tutofinder.app.dto.PagoDto;
import com.tutofinder.app.dto.create.CreatePagoDto;
import com.tutofinder.app.exception.BookingException;
import com.tutofinder.app.response.BookingResponse;
import com.tutofinder.app.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"http://localhost:4200"})
@RestController
@RequestMapping(path = "/tutofinder")
public class PagoController {

    @Autowired
    PagoService pagoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pagos/{pagoId}")
    public BookingResponse<PagoDto> getPagoById(@PathVariable Long pagoId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                pagoService.getPagoById(pagoId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pagos")
    public BookingResponse<List<PagoDto>> getPagos() throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                pagoService.getPagos());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/pagos")
    public BookingResponse<PagoDto> createPago(@RequestBody @Valid CreatePagoDto createPagoDto) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                pagoService.createPago(createPagoDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/pagos/{pagoId}")
    public BookingResponse<PagoDto> updatePago(@PathVariable Long pagoId, @RequestBody @Valid CreatePagoDto createPagoDto) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                pagoService.updatePago(createPagoDto, pagoId));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/pagos/{pagoId}")
    public BookingResponse<String> deletePago(@PathVariable Long pagoId) throws BookingException {
        return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                pagoService.deletePago(pagoId));
    }
}
