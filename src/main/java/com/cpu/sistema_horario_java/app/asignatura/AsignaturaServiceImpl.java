package com.cpu.sistema_horario_java.app.asignatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.carga.CargaAcademicaRepository;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.ASIGNATURA;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    AsignaturaRepository repo;
    @Autowired
    CargaAcademicaRepository car;

    @Autowired
    AsignaturaMapper matMapper;

    @Override
    public AsignaturaDTO buscar(Long id) {
        Optional<Asignatura> asignatura = repo.findById(id);

        if (asignatura.isPresent()) {
            return matMapper.toDTO(asignatura.get());
        }
        throw exception(ASIGNATURA, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<AsignaturaDTO> listar() {
        return repo.findAll().stream().map(Asignatura -> matMapper.toDTO(Asignatura)).collect(Collectors.toList());
    }

    @Override
    public AsignaturaDTO guardar(AsignaturaDTO dto) {
        Optional<AsignaturaDTO> asignatura = repo.findByNombre(dto.getNombre());

        if (!asignatura.isPresent()) {
            return matMapper.toDTO(repo.save(matMapper.toModel(dto)));
        }

        throw exception(ASIGNATURA, DUPLICATE_ENTITY, dto.getNombre());
    }

    @Override
    public AsignaturaDTO reemplazar(Long id, AsignaturaDTO asignatura) {

        return repo.findById(id).map(m -> {
            return matMapper.toDTO(repo.save(matMapper.toModel(asignatura, m)));
        }).orElseGet(() -> {
            asignatura.setId(id);
            return matMapper.toDTO(repo.save(matMapper.toModel(asignatura)));
        });
    }

    @Override
    public void eliminar(Long id) {
        Optional<Asignatura> asignatura = repo.findById(id);

        if (asignatura.isPresent()) {
            repo.delete(asignatura.get());
        }
        throw exception(ASIGNATURA, ENTITY_NOT_FOUND, id.toString());
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