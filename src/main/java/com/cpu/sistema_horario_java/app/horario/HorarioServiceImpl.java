package com.cpu.sistema_horario_java.app.horario;

import com.cpu.sistema_horario_java.app.util.types.Dia;
import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.types.Estatus;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.CURSO;
import static com.cpu.sistema_horario_java.app.util.types.EntityType.CURSO_CARGA_ACADEMICA;
import static com.cpu.sistema_horario_java.app.util.types.EntityType.HORARIO;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.carga.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.curso.Curso;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;
import com.cpu.sistema_horario_java.app.periodo.Periodo;
import com.cpu.sistema_horario_java.app.periodo.PeriodoRepository;
import com.cpu.sistema_horario_java.app.util.ListUtil;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@SuppressWarnings("uncheked")
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
    PeriodoRepository pr;

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
    public List<HorarioDTO> horariosPorCurso(Long id) {
        final Optional<Curso> curso = cr.findById(id);
        if (curso.isPresent()) {
            return repo.findAll().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
        } else {
            throw exception(HORARIO, ENTITY_NOT_FOUND, id.toString());
        }
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
        } else {
            throw exception(HORARIO, ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    @Transactional
    public void generarHorarios() {
        generarHorariosPorCurso(null);
    }

    @Override
    @Transactional
    public void generarHorariosPorCurso(Long id) {

        int tries = 0;
        List<CargaAcademica> cargasAcademicas = new ArrayList<>();
        List<Periodo> bloquesAcademicos = pr.periodosParaAsignar();

        List<Dia> dias;

        if (id == null) {
            cargasAcademicas = car.findAll();
        } else {
            Optional<Curso> curso = cr.findById(id);
            if (curso.isPresent()) {
                Optional<List<CargaAcademica>> cargas = car.cargasPorCurso(curso.get());
                if (cargas.isPresent()) {
                    cargasAcademicas = cargas.get();
                } else {
                    throw exception(CURSO_CARGA_ACADEMICA, ENTITY_NOT_FOUND, id.toString());
                }
            } else {
                throw exception(CURSO, ENTITY_NOT_FOUND, id.toString());
            }
        }

        for (CargaAcademica cargaAcademica : cargasAcademicas) {
            Integer horas = cargaAcademica.getHoras();
            dias = ListUtil.toList(cargaAcademica.getCurso().getDias());

            while (horas > 0) {
                Horario horario;
                Dia diaTentativo;
                Periodo periodoTentativo;

                if (horas.equals(1)) {
                    horario = new Horario();
                    diaTentativo = (Dia) ListUtil.getRandomElement(dias);
                    periodoTentativo = (Periodo) ListUtil.getRandomElement(bloquesAcademicos);

                    while (alreadyPersisted(diaTentativo.toString(), periodoTentativo.getId(),
                            cargaAcademica.getCurso().getId())) {
                        if (tries > 200) {
                            break;
                        }

                        diaTentativo = (Dia) ListUtil.getRandomElement(dias);
                        periodoTentativo = (Periodo) ListUtil.getRandomElement(bloquesAcademicos);
                        tries++;
                    }

                    horario.setCargaAcademica(cargaAcademica);
                    horario.setDia(diaTentativo);
                    horario.setPeriodo(periodoTentativo);

                    log.info("Guardando horario: " + repo.save(horario));

                    horas--;
                } else {

                    diaTentativo = (Dia) ListUtil.getRandomElement(dias);
                    List<Periodo> parBloqueTentativo = getValidPeriodoPair(bloquesAcademicos);

                    while (alreadyPersisted(diaTentativo.toString(), parBloqueTentativo.get(0).getId(),
                            cargaAcademica.getCurso().getId())
                            || alreadyPersisted(diaTentativo.toString(), parBloqueTentativo.get(1).getId(),
                                    cargaAcademica.getCurso().getId())) {
                        if (tries > 200) {
                            break;
                        }

                        diaTentativo = (Dia) ListUtil.getRandomElement(dias);
                        parBloqueTentativo = getValidPeriodoPair(bloquesAcademicos);
                        tries++;
                    }

                    for (Periodo bloque : parBloqueTentativo) {
                        horario = new Horario();

                        periodoTentativo = bloque;
                        horario.setCargaAcademica(cargaAcademica);
                        horario.setDia(diaTentativo);
                        horario.setPeriodo(periodoTentativo);

                        log.info("Guardando horario: " + repo.save(horario));
                        horas--;
                    }

                }
            }
            cargaAcademica.setEstatus(Estatus.PROGRAMADA);
            car.save(cargaAcademica);
        }
    }

    private List<Periodo> getValidPeriodoPair(List<Periodo> bloquesAcademicos) {
        List<Periodo> par = new ArrayList<>();
        Periodo bloque = (Periodo) ListUtil.getRandomElement(bloquesAcademicos);
        Periodo siguienteBloque = pr.getOne(bloque.getId() + 1L);

        while (siguienteBloque.getEstatus() == Boolean.FALSE) {
            bloque = (Periodo) ListUtil.getRandomElement(bloquesAcademicos);
            siguienteBloque = pr.getOne(bloque.getId() + 1L);
        }

        par.add(bloque);
        par.add(siguienteBloque);

        return par;
    }

    private boolean alreadyPersisted(String dia, Long periodo, Long curso) {
        return repo.getHorarioAsignado(dia, periodo, curso).isPresent();
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