package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.*;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.service.IOdontologoService;
import com.backend.clinica_odontologica.service.IPacienteService;
import com.backend.clinica_odontologica.service.ITurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    @Autowired
    private ITurnoService turnoService;

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IOdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnTurno_yRetornarSuId() {
        PacienteSalidaDto paciente = registrarPaciente();
        OdontologoSalidaDto odontologo = registrarOdontologo();

        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.now().plusDays(1), new OdontologoIdDto(odontologo.getId()), new PacienteIdDto(paciente.getId()));
        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        assertNotNull(turnoSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeTurnos() {
        List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
        assertFalse(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElTurnoConId1() {
        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDeTurnos() {
        List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
        assertTrue(listadoDeTurnos.isEmpty());
    }

    private PacienteSalidaDto registrarPaciente() {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("NombrePaciente", "ApellidoPaciente", 123456, LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Calle", 123, "Localidad", "Provincia"));
        return pacienteService.registrarPaciente(pacienteEntradaDto);
    }

    private OdontologoSalidaDto registrarOdontologo() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("NombreOdontologo", "ApellidoOdontologo", "123456");
        return odontologoService.registrarOdontologo(odontologoEntradaDto);
    }

}