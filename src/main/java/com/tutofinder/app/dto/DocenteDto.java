package com.tutofinder.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DocenteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String domicilio;
    private String correo;
    private String numeroCuenta;
    private Boolean membresia;
    private List<TutoriaDto> tutorias;
    private Date createAt;
}
