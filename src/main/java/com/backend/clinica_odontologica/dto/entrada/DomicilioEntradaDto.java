package com.backend.clinica_odontologica.dto.entrada;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class DomicilioEntradaDto {

    @NotBlank(message = "Debe proveerse el nombre de la calle del domicilio")
    private String calle;

    @Positive(message = "El numero no puede ser nulo o menor a cero")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private int numero;

    @NotBlank(message = "Debe proveerse la localidad del domicilio")
    private String localidad;

    @NotBlank(message = "Debe proveerse la provincia del domicilioo")
    private String provincia;

    public DomicilioEntradaDto() {
    }

    public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
