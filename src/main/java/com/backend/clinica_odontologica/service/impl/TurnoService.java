package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica_odontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinica_odontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinica_odontologica.entity.Odontologo;
import com.backend.clinica_odontologica.entity.Paciente;
import com.backend.clinica_odontologica.entity.Turno;
import com.backend.clinica_odontologica.exceptions.BadRequestException;
import com.backend.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.service.IOdontologoService;
import com.backend.clinica_odontologica.service.IPacienteService;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private final TurnoRepository turnoRepository;
    private final IPacienteService pacienteService;
    private final IOdontologoService odontologoService;
    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, IPacienteService pacienteService, IOdontologoService odontologoService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {

        LOGGER.info("TurnoEntradaDto: " + JsonPrinter.toString(turnoEntradaDto));

        PacienteSalidaDto pacienteSalidaDto = obtenerPacientePorId(turnoEntradaDto.getPaciente().getId());
        OdontologoSalidaDto odontologoSalidaDto = obtenerOdontologoPorId(turnoEntradaDto.getOdontologo().getId());

        Odontologo odontologo = modelMapper.map(odontologoSalidaDto, Odontologo.class);
        Paciente paciente = modelMapper.map(pacienteSalidaDto, Paciente.class);

        Turno turno = new Turno(turnoEntradaDto.getFechaYHora(), odontologo, paciente);

        Turno turnoGuardado = turnoRepository.save(turno);
        LOGGER.info("TurnoEntidad: " + JsonPrinter.toString(turnoGuardado));

        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoGuardado, TurnoSalidaDto.class);
        LOGGER.info("TurnoSalidaDto: " + JsonPrinter.toString(turnoSalidaDto));
        return turnoSalidaDto;
    }


    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoSalidaDto> turnosSalidaDto = turnos.stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnosSalidaDto));
        return turnosSalidaDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("No se ha encontrado el turno con id {}", id);

        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe registro de turno con id " + id);
        }
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws ResourceNotFoundException {
        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoAActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAActualizar != null){

            turnoRecibido.setId(turnoAActualizar.getId());

            PacienteSalidaDto pacienteSalidaDto = obtenerPacientePorId(turnoEntradaDto.getPaciente().getId());
            OdontologoSalidaDto odontologoSalidaDto = obtenerOdontologoPorId(turnoEntradaDto.getOdontologo().getId());

            Odontologo odontologo = modelMapper.map(odontologoSalidaDto, Odontologo.class);
            Paciente paciente = modelMapper.map(pacienteSalidaDto, Paciente.class);

            turnoRecibido.setOdontologo(odontologo);
            turnoRecibido.setPaciente(paciente);

            Turno turnoActualizado = turnoRepository.save(turnoRecibido);
            turnoSalidaDto = modelMapper.map(turnoActualizado, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("Turno no encontrado");
        }

        return turnoSalidaDto;
    }

    private PacienteSalidaDto obtenerPacientePorId(Long id) {
        PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId(id);
        if (pacienteSalidaDto == null) {
            throw new BadRequestException("No existe el paciente con ID: " + id);
        }
        return pacienteSalidaDto;
    }

    private OdontologoSalidaDto obtenerOdontologoPorId(Long id) {
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(id);
        if (odontologoSalidaDto == null) {
            throw new BadRequestException("No se existe el odontólogo con ID: " + id);
        }
        return odontologoSalidaDto;
    }
}
