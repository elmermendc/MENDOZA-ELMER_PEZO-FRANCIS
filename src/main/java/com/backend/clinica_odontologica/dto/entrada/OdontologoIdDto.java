package com.backend.clinica_odontologica.dto.entrada;

import javax.validation.constraints.NotNull;

public class OdontologoIdDto {
    @NotNull(message = "Debe especificarse el id del odontólogo")
    private Long id;

    public OdontologoIdDto() {
    }
    public OdontologoIdDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
