package com.cpu.sistema_horario_java.app.curso;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.horario.HorarioRepository;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.CURSO;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    CursoRepository repo;

    @Autowired
    HorarioRepository hr;

    @Autowired
    CursoMapper mapper;

    @Override
    public CursoDTO buscar(Long id) {
        Optional<Curso> model = repo.findById(id);

        if (model.isPresent()) {
            return mapper.toDTO(model.get());
        }
        throw exception(CURSO, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<CursoDTO> listar() {
        return repo.findAll().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
    }

    @Override
    public CursoDTO guardar(CursoDTO dto) {
        Optional<CursoDTO> model = repo.findByNombre(dto.getNombre());

        if (!model.isPresent()) {
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        }

        throw exception(CURSO, DUPLICATE_ENTITY, dto.getNombre());
    }

    @Override
    public CursoDTO actualizar(Long id, CursoDTO dto) {

        return repo.findById(id).map(m -> {
            return mapper.toDTO(repo.save(mapper.toModel(dto, m)));
        }).orElseGet(() -> {
            dto.setId(id);
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        });
    }

    @Override
    public void eliminar(Long id) {
        Optional<Curso> model = repo.findById(id);

        if (model.isPresent()) {
            hr.deleteAll();
            repo.deleteAllCargaAssociatedToCurso(model.get());
            repo.delete(model.get());
        } else {
            throw exception(CURSO, ENTITY_NOT_FOUND, id.toString());
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