package com.cpu.sistema_horario_java.app.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpu.sistema_horario_java.util.exception.HorarioException;

import static com.cpu.sistema_horario_java.util.exception.EntityType.MATERIA;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    MateriaRepository repo;

    @Autowired
    MateriaMapper mapper;

    public MateriaDTO crear(MateriaDTO dto) {

        MateriaDTO materia = repo.findByNombe(dto.getNombre());

        if (materia == null) {
            return mapper.toDTO(mapper.toModel(dto));

        }

        throw HorarioException.throwException(MATERIA, ENTITY_NOT_FOUND, dto.getNombre());

    }
}