package com.cpu.sistema_horario_java.app.docente;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.DOCENTE;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    DocenteRepository repo;

    @Autowired
    DocenteMapper mapper;

    @Override
    public DocenteDTO buscar(Long id) {
        Optional<Docente> model = repo.findById(id);

        if (model.isPresent()) {
            return mapper.toDTO(model.get());
        }
        throw exception(DOCENTE, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<DocenteDTO> listar() {
        return repo.findAll().stream().map(Docente -> mapper.toDTO(Docente)).collect(Collectors.toList());
    }

    @Override
    public DocenteDTO guardar(DocenteDTO dto) {
        Optional<Docente> model = repo.findByCedula(dto.getCedula());

        if (!model.isPresent()) {
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        }

        throw exception(DOCENTE, DUPLICATE_ENTITY, dto.getCedula());
    }

    @Override
    public DocenteDTO actualizar(Long id, DocenteDTO dto) {

        return repo.findById(id).map(m -> {
            return mapper.toDTO(repo.save(mapper.toModel(dto, m)));
        }).orElseGet(() -> {
            dto.setId(id);
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        });
    }

    @Override
    public void eliminar(Long id) {
        Optional<Docente> model = repo.findById(id);

        if (model.isPresent()) {
            repo.delete(model.get());
        } else {
            throw exception(DOCENTE, ENTITY_NOT_FOUND, Long.toString(id));
        }
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