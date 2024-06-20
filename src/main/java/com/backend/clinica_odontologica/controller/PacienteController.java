package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto){
        return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteEntradaDto), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes(){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.actualizarPaciente(pacienteEntradaDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarPaciente(@RequestParam Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
