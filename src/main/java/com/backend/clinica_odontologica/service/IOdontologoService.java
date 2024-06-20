package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto);
    List<OdontologoSalidaDto> listarOdontologos();
    OdontologoSalidaDto buscarOdontologoPorId(Long id);
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
    OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) throws ResourceNotFoundException;
}
