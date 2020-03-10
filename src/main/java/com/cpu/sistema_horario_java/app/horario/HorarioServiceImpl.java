package com.cpu.sistema_horario_java.app.horario;

import com.cpu.sistema_horario_java.app.util.types.Dia;
import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.HORARIO;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.SortedSet;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.carga.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.curso.Curso;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;
import com.cpu.sistema_horario_java.app.periodo.Period;
import com.cpu.sistema_horario_java.app.periodo.PeriodRepository;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    HorarioRepository repo;

    @Autowired
    CargaAcademicaRepository car;

    @Autowired
    DocenteRepository dr;

    @Autowired
    AsignaturaRepository ar;

    @Autowired
    PeriodRepository pr;

    @Autowired
    CursoRepository cr;

    @Autowired
    HorarioMapper mapper;

    @Override
    public HorarioDTO buscar(final Long id) {
        final Optional<Horario> model = repo.findById(id);

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
    public HorarioDTO guardar(final HorarioDTO dto) {
        final Optional<Horario> model = repo.findById(dto.getId());

        if (!model.isPresent()) {
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        }

        throw exception(HORARIO, DUPLICATE_ENTITY, dto.getId().toString());
    }

    @Override
    public HorarioDTO actualizar(final Long id, final HorarioDTO dto) {

        return repo.findById(id).map(m -> {
            return mapper.toDTO(repo.save(mapper.toModel(dto, m)));
        }).orElseGet(() -> {
            dto.setId(id);
            return mapper.toDTO(repo.save(mapper.toModel(dto)));
        });
    }

    @Override
    public void eliminar(final Long id) {
        final Optional<Horario> model = repo.findById(id);

        if (model.isPresent()) {
            repo.delete(model.get());
        }
        throw exception(HORARIO, ENTITY_NOT_FOUND, id.toString());
    }

    public HorarioDTO generar() {

        Dia[] dias = Dia.values();

        List<Period> periodos = pr.findAll().stream().filter(Period::getEstatus).collect(Collectors.toList());

        List<Curso> cursos = cr.findAll();

        for (final Dia dia : dias) {
            Horario horario = new Horario();
            for (Curso curso : cursos) {
                horario.setDia(dia);
                horario.setPeriodo(getRandomPeriod(periodos));
                horario.setPeriodo(getRandomPeriod(periodos));
            }

        }

        return null;
    }

    private Curso getRandomCurso(List<Curso> lista) {
        int randomIndex = new Random().nextInt(lista.size());
        Curso elemento = lista.get(randomIndex);
        lista.remove(randomIndex);
        return elemento;
    }

    private Period getRandomPeriod(List<Period> lista) {
        int randomIndex = new Random().nextInt(lista.size());
        Period elemento = lista.get(randomIndex);
        lista.remove(randomIndex);
        return elemento;
    }

    private Asignatura getRandomAsignatura(List<Asignatura> lista) {
        int randomIndex = new Random().nextInt(lista.size());
        Asignatura elemento = lista.get(randomIndex);
        lista.remove(randomIndex);
        return elemento;
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    static RuntimeException exception(final EntityType entityType, final ExceptionType exceptionType,
            final String... args) {
        return SystemException.throwException(entityType, exceptionType, args);
    }
}