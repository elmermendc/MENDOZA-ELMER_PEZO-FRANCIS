package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.service.IPacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private IPacienteService pacienteService;


    @Test
    @Order(1)
    void deberiaRegistrarseUnPacienteDeNombreJuan_yRetornarSuId(){

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 123456, LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Calle", 123, "Localidad", "Provincia"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        //assert
        assertNotNull(pacienteSalidaDto);
        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Juan", pacienteSalidaDto.getNombre());
    }


    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDePacientes(){
        List<PacienteSalidaDto> listadoDePacientes = pacienteService.listarPacientes();
        assertFalse(listadoDePacientes.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElPacienteConId1(){

        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDePacientes(){
        List<PacienteSalidaDto> listadoDePacientes = pacienteService.listarPacientes();
        assertTrue(listadoDePacientes.isEmpty());
    }



}