package com.cpu.sistema_horario_java.app.profesor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpu.sistema_horario_java.util.exception.HorarioException;

import static com.cpu.sistema_horario_java.util.exception.EntityType.PROFESOR;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    ProfesorRepository repo;

    @Autowired
    ProfesorMapper mapper;

    public ProfesorDTO crear(ProfesorDTO dto) {

        ProfesorDTO profesor = repo.findByApellido(dto.getApellido());

        if (profesor == null) {
            return mapper.toDTO(mapper.toModel(dto));
        }

        throw HorarioException.throwException(PROFESOR, ENTITY_NOT_FOUND, dto.getNombre());

    }
}