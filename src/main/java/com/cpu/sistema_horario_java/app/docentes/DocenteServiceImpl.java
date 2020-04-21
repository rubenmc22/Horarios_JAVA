package com.cpu.sistema_horario_java.app.docentes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.cargas.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.horarios.Horario;
import com.cpu.sistema_horario_java.app.horarios.HorarioRepository;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.DOCENTE;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    DocenteRepository repo;

    @Autowired
    HorarioRepository hr;

    @Autowired
    CargaAcademicaRepository car;

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
    public void eliminar() {
        List<Docente> docentes = repo.findAll();

        log.info("ELIMINAR DOCENTE - TODO - BUSCANDO");
        if (docentes.size() > 0) {

            log.info("ELIMINANDO HORARIOS");
            hr.deleteAll();

            log.info("ELIMINANDO CARGAS ACADEMICAS");
            car.deleteAll();

            log.info("ELIMINANDO DOCENTES");
            repo.deleteAll();
            log.info("DOCENTES ELIMINANDOS");
        } else {
            log.info("NO HAY DOCENTES PARA ELIMINAR");
        }
    }

    @Override
    public void eliminar(final Long ID) {
        Optional<Docente> model = repo.findById(ID);

        log.info("ELIMINAR DOCENTE-ID: " + ID + " BUSCANDO");
        if (model.isPresent()) {

            log.info("ENCONTRADO - ELIMINANDO HORARIOS ASOCIADOS");
            Optional<List<Horario>> horariosPorDocente = hr.horariosPorDocente(ID);
            if (horariosPorDocente.isPresent()) {
                hr.deleteAll(horariosPorDocente.get());
            }
            log.info("ELIMINANDO CARGAS ACADEMICAS ASOCIADAS");
            car.eliminarCargasPorDocente(model.get());

            log.info("ELIMINANDO DOCENTE-ID: " + ID);
            repo.delete(model.get());
            log.info("DOCENTE-ID: " + ID + " ELIMINADO");
        } else {
            log.info("DOCENTE " + ID + " NO ENCONTRADO");
            throw exception(DOCENTE, ENTITY_NOT_FOUND, ID.toString());
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