package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.service.IOdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
    @Autowired
    private IOdontologoService odontologoService;

    @Test
    @Order(1)
    public void deberiaRegistrarseUnOdontologoDeNombreElmer_yRetornarSuId() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("ABC123","Elmer","Mendoza");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Elmer", odontologoSalidaDto.getNombre());
    }
    @Test
    @Order(2)
    public void deberiaDevolverListaDeOdontologosConUnElemento() {
        List<OdontologoSalidaDto> odontologos = odontologoService.listarOdontologos();
        assertEquals(1, odontologos.size());
    }
    @Test
    @Order(3)
    void deberiaEliminarseElOdontologoConId1() {
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

}