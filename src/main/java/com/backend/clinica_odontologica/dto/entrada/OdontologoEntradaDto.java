package com.backend.clinica_odontologica.dto.entrada;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    @NotBlank(message = "Debe especificarse la matrícula del odontólogo")
    private String matricula;

    @NotBlank(message = "Debe especificarse el nombre del odontólogo")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String nombre;

    @NotBlank(message = "Debe especificarse el apellido del odontólogo")
    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
