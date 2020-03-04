package com.cpu.sistema_horario_java.app.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.util.EntityType;
import com.cpu.sistema_horario_java.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.util.exception.SystemException;

import static com.cpu.sistema_horario_java.util.EntityType.MATERIA;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    MateriaRepository repo;

    @Autowired
    MateriaMapper matMapper;

    @Override
    public MateriaDTO buscar(Long id) {
        Optional<Materia> materia = repo.findById(id);

        if (materia.isPresent()) {
            return matMapper.toDTO(materia.get());
        }
        throw exception(MATERIA, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<MateriaDTO> listar() {
        return repo.findAll().stream().map(materia -> matMapper.toDTO(materia)).collect(Collectors.toList());
    }

    @Override
    public MateriaDTO guardar(MateriaDTO dto) {
        Optional<MateriaDTO> materia = repo.findByNombre(dto.getNombre());

        if (!materia.isPresent()) {
            return matMapper.toDTO(repo.save(matMapper.toModel(dto)));
        }

        throw exception(MATERIA, DUPLICATE_ENTITY, dto.getNombre());
    }

    @Override
    public MateriaDTO actualizar(Long id, MateriaDTO materia) {

        return repo.findById(id).map(m -> {
            return matMapper.toDTO(repo.save(matMapper.toModel(materia, m)));
        }).orElseGet(() -> {
            materia.setId(id);
            return matMapper.toDTO(repo.save(matMapper.toModel(materia)));
        });
    }

    @Override
    public void eliminar(Long id) {
        Optional<Materia> materia = repo.findById(id);

        if (materia.isPresent()) {
            repo.delete(materia.get());
        }
        throw exception(MATERIA, ENTITY_NOT_FOUND, id.toString());
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