package com.backend.clinica_odontologica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {

    @NotNull(message = "La fecha y hora del turno no pueden ser nulas")
    @Future(message = "La fecha y hora del turno deben ser en el futuro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaYHora;

    @NotNull(message = "Debe especificarse el odontólogo")
    @Valid
    private OdontologoIdDto odontologo;

    @NotNull(message = "Debe especificarse el paciente")
    @Valid
    private PacienteIdDto paciente;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(LocalDateTime fechaYHora, OdontologoIdDto odontologo, PacienteIdDto paciente) {
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public OdontologoIdDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoIdDto odontologo) {
        this.odontologo = odontologo;
    }

    public PacienteIdDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteIdDto paciente) {
        this.paciente = paciente;
    }
}

