package com.cpu.sistema_horario_java.app.cursos;

import com.cpu.sistema_horario_java.app.util.types.Dia;
import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.cargas.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.horarios.Horario;
import com.cpu.sistema_horario_java.app.horarios.HorarioRepository;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.CURSO;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    CursoRepository repo;

    @Autowired
    HorarioRepository hr;

    @Autowired
    CargaAcademicaRepository car;

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
        Optional<Curso> model = repo.findByNombre(dto.getNombre());

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
    public void eliminar() {
        List<Curso> cursos = repo.findAll();

        log.info("ELIMINAR CURSO - TODO - BUSCANDO");
        if (cursos.size() > 0) {

            log.info("ELIMINANDO HORARIOS");
            hr.deleteAll();

            log.info("ELIMINANDO CARGAS ACADEMICAS");
            car.deleteAll();

            log.info("ELIMINANDO CURSOS");
            repo.deleteAll();
            log.info("CURSOS ELIMINANDOS");
        } else {
            log.info("NO HAY CURSOS PARA ELIMINAR");
        }
    }

    @Override
    public void eliminar(final Long ID) {
        Optional<Curso> model = repo.findById(ID);

        log.info("ELIMINAR CURSO-ID: " + ID + " BUSCANDO");
        if (model.isPresent()) {

            log.info("ENCONTRADO - ELIMINANDO HORARIOS ASOCIADOS");
            Optional<List<Horario>> horariosPorCurso = hr.horariosPorCurso(ID);
            if (horariosPorCurso.isPresent()) {
                hr.deleteAll(horariosPorCurso.get());
            }
            log.info("ELIMINANDO CARGAS ACADEMICAS ASOCIADAS");
            car.eliminarCargasPorCurso(model.get());

            log.info("ELIMINANDO CURSO-ID: " + ID);
            repo.delete(model.get());
            log.info("CURSO-ID: " + ID + " ELIMINADO");
        } else {
            log.info("CURSO " + ID + " NO ENCONTRADO");
            throw exception(CURSO, ENTITY_NOT_FOUND, ID.toString());
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

    @Override
    public Set<Dia> diasDisponibles(Long id) {
        return getCurso(id).getDias();
    }

    private Curso getCurso(final Long ID) {
        Optional<Curso> curso = repo.findById(ID);
        if (curso.isPresent()) {
            return curso.get();
        } else {
            throw exception(CURSO, ENTITY_NOT_FOUND, ID.toString());
        }
    }

}