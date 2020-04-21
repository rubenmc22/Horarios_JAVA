package com.cpu.sistema_horario_java.app.asignaturas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.cargas.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.horarios.Horario;
import com.cpu.sistema_horario_java.app.horarios.HorarioRepository;
import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.ASIGNATURA;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Slf4j
@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    AsignaturaRepository repo;

    @Autowired
    CargaAcademicaRepository car;

    @Autowired
    HorarioRepository hr;

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
        Optional<AsignaturaDTO> model = repo.findByNombre(dto.getNombre());

        if (!model.isPresent()) {
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
    public void eliminar() {
        List<Asignatura> asignaturas = repo.findAll();

        log.info("ELIMINAR ASIGNATURA - TODO - BUSCANDO");
        if (asignaturas.size() > 0) {

            log.info("ELIMINANDO HORARIOS");
            hr.deleteAll();

            log.info("ELIMINANDO CARGAS ACADEMICAS");
            car.deleteAll();

            log.info("ELIMINANDO ASIGNATURAS");
            repo.deleteAll();
            log.info("ASIGNATURAS ELIMINANDAS");
        } else {
            log.info("NO HAY ASIGNATURAS PARA ELIMINAR");
        }
    }

    @Override
    public void eliminar(final Long ID) {
        Optional<Asignatura> model = repo.findById(ID);

        log.info("ELIMINAR ASIGNATURA-ID: " + ID + " BUSCANDO");
        if (model.isPresent()) {

            log.info("ENCONTRADO - ELIMINANDO HORARIOS ASOCIADOS");
            Optional<List<Horario>> horariosPorAsignatura = hr.horariosPorAsignatura(ID);
            if (horariosPorAsignatura.isPresent()) {
                hr.deleteAll(horariosPorAsignatura.get());
            }
            log.info("ELIMINANDO CARGAS ACADEMICAS ASOCIADAS");
            car.eliminarCargasPorAsignatura(model.get());

            log.info("ELIMINANDO ASIGNATURA-ID: " + ID);
            repo.delete(model.get());
            log.info("ASIGNATURA-ID: " + ID + " ELIMINADO");
        } else {
            log.info("ASIGNATURA " + ID + " NO ENCONTRADO");
            throw exception(ASIGNATURA, ENTITY_NOT_FOUND, ID.toString());
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