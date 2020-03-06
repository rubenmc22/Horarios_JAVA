package com.cpu.sistema_horario_java.app.carga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.util.EntityType;
import com.cpu.sistema_horario_java.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.util.exception.SystemException;

import static com.cpu.sistema_horario_java.util.EntityType.CARGA;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
public class CargaServiceImpl implements CargaService {

    @Autowired
    CargaRepository repo;

    @Autowired
    CargaMapper mapper;

    @Override
    public CargaDTO buscar(Long id) {
        Optional<Carga> model = repo.findById(id);

        if (model.isPresent()) {
            return mapper.toDTO(model.get());
        }
        throw exception(CARGA, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<CargaDTO> listar() {
        return repo.findAll().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
    }

    @Override
    public CargaDTO guardar(CargaDTO dto) {
        Optional<CargaDTO> model = repo.findByNombre(dto.getNombre());

        if (!model.isPresent()) {
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        }

        throw exception(CARGA, DUPLICATE_ENTITY, dto.getNombre());
    }

    @Override
    public CargaDTO actualizar(Long id, CargaDTO dto) {

        return repo.findById(id).map(m -> {
            return mapper.toDTO(repo.save(mapper.toModel(dto, m)));
        }).orElseGet(() -> {
            dto.setId(id);
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        });
    }

    @Override
    public void eliminar(Long id) {
        Optional<Carga> model = repo.findById(id);

        if (model.isPresent()) {
            repo.delete(model.get());
        }
        throw exception(CARGA, ENTITY_NOT_FOUND, id.toString());
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