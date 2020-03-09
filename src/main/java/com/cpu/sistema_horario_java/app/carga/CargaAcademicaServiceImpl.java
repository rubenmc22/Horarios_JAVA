package com.cpu.sistema_horario_java.app.carga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.CARGA_ACADEMICA;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class CargaAcademicaServiceImpl implements CargaAcademicaService {

    @Autowired
    CargaAcademicaRepository repo;

    @Autowired
    CargaAcademicaMapper mapper;

    @Override
    public CargaAcademicaDTO buscar(Long id) {
        Optional<CargaAcademica> model = repo.findById(id);

        if (model.isPresent()) {
            return mapper.toDTO(model.get());
        }
        throw exception(CARGA_ACADEMICA, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<CargaAcademicaDTO> listar() {
        return repo.findAll().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
    }

    @Override
    public CargaAcademicaDTO guardar(CargaAcademicaDTO dto) {
        Optional<CargaAcademica> model = dto.getId().equals(null) ? Optional.empty() : repo.findById(dto.getId());

        if (!model.isPresent()) {
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        }

        throw exception(CARGA_ACADEMICA, DUPLICATE_ENTITY, dto.toString());
    }

    @Override
    public CargaAcademicaDTO actualizar(Long id, CargaAcademicaDTO dto) {

        return repo.findById(id).map(m -> {
            return mapper.toDTO(repo.save(mapper.toModel(dto, m)));
        }).orElseGet(() -> {
            dto.setId(id);
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        });
    }

    @Override
    public void eliminar(Long id) {
        Optional<CargaAcademica> model = repo.findById(id);

        if (model.isPresent()) {
            repo.delete(model.get());
        }
        throw exception(CARGA_ACADEMICA, ENTITY_NOT_FOUND, id.toString());
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