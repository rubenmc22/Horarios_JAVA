package com.cpu.sistema_horario_java.app.materia;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpu.sistema_horario_java.util.exception.HorarioException;

import static com.cpu.sistema_horario_java.util.exception.EntityType.MATERIA;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    MateriaRepository repo;

    @Autowired
    MateriaMapper matMapper;
    @Autowired
    ModelMapper modMapper;

    public MateriaDTO crear(MateriaDTO dto) {

        MateriaDTO materia = repo.findByNombe(dto.getNombre());

        if (materia == null) {
            return matMapper.toDTO(matMapper.toModel(dto));

        }

        throw HorarioException.throwException(MATERIA, ENTITY_NOT_FOUND, dto.getNombre());

    }

    @Override
    public Set<MateriaDTO> getAllMaterias() {
        return repo.findAll().stream()
        .map(materia -> modMapper.map(materia, MateriaDTO.class))
        .collect(Collectors.toCollection(TreeSet::new));
    }
}