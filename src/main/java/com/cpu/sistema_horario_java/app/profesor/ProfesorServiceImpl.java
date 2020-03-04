package com.cpu.sistema_horario_java.app.profesor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return null;
    }
}