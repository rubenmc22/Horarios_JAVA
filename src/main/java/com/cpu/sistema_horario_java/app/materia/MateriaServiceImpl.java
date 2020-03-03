package com.cpu.sistema_horario_java.app.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    MateriaRepository repo;

    @Autowired
    MateriaMapper mapper;

    public MateriaDTO crear(MateriaDTO dto) {

        return mapper.toDTO(mapper.toModel(dto));

    }
}