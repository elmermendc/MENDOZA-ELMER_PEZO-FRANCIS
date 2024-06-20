package com.backend.clinica_odontologica.dto.entrada;

import javax.validation.constraints.NotNull;

public class PacienteIdDto {
    @NotNull(message = "Debe especificarse el id del paciente")
    private Long id;

    public PacienteIdDto() {
    }
    public PacienteIdDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
