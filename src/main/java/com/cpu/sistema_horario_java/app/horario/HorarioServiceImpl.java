package com.cpu.sistema_horario_java.app.horario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.util.EntityType;
import com.cpu.sistema_horario_java.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.util.exception.SystemException;

import static com.cpu.sistema_horario_java.util.EntityType.HORARIO;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    HorarioRepository repo;

    @Autowired
    HorarioMapper mapper;

    @Override
    public HorarioDTO buscar(Long id) {
        Optional<Horario> model = repo.findById(id);

        if (model.isPresent()) {
            return mapper.toDTO(model.get());
        }
        throw exception(HORARIO, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<HorarioDTO> listar() {
        return repo.findAll().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
    }

    @Override
    public HorarioDTO guardar(HorarioDTO dto) {
        Optional<Horario> model = repo.findById(dto.getId());

        if (!model.isPresent()) {
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        }

        throw exception(HORARIO, DUPLICATE_ENTITY, dto.getId().toString());
    }

    @Override
    public HorarioDTO actualizar(Long id, HorarioDTO dto) {

        return repo.findById(id).map(m -> {
            return mapper.toDTO(repo.save(mapper.toModel(dto, m)));
        }).orElseGet(() -> {
            dto.setId(id);
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        });
    }

    @Override
    public void eliminar(Long id) {
        Optional<Horario> model = repo.findById(id);

        if (model.isPresent()) {
            repo.delete(model.get());
        }
        throw exception(HORARIO, ENTITY_NOT_FOUND, id.toString());
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    static RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return SystemException.throwException(entityType, exceptionType, args);
    }
}