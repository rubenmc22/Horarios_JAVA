package com.cpu.sistema_horario_java.app.horario;

import com.cpu.sistema_horario_java.app.util.types.Dia;
import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.HORARIO;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.carga.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;
import com.cpu.sistema_horario_java.app.periodo.Periodo;
import com.cpu.sistema_horario_java.app.periodo.PeriodoRepository;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    @Transactional
    public void generar() {

        List<Periodo> periodos = pr.findAll();
        List<Dia> dias = new ArrayList<>();
        for (Dia dia : Dia.values()) {
            if (dia.getNumeroDia() < 6) {
                dias.add(dia);
            }
        }
        List<CargaAcademica> cargasAcademicas = car.findAll();

        Collections.shuffle(dias);
        Collections.shuffle(periodos);
        Collections.shuffle(cargasAcademicas);

        for (Periodo bloque : periodos) {
            otro_bloque:

            for (Dia dia : dias) {
                log.info("dia: " + dia);
                log.info("bloque: " + bloque);

                if (!bloque.getEstatus()) {
                    log.info("bloque inactivo: " + bloque);
                    break otro_bloque;
                }

                if (cargasAcademicas.size() <= 0) {
                    break;
                }
                boolean primeraVuelta = true;
                CargaAcademica cargaAleatoria = getRandomCargaAcademica(cargasAcademicas);
                int horas = cargaAleatoria.getHoras();

                while (horas >= 1) {
                    Horario horario1 = new Horario();
                    Periodo bloqueParaAsignar = new Periodo();

                    if (primeraVuelta) {
                        bloqueParaAsignar = bloque;
                        primeraVuelta = false;
                    } else {
                        bloqueParaAsignar = getRandomPeriod(periodos, false);
                    }

                    horario1.setDia(dia);
                    horario1.setPeriodo(bloqueParaAsignar);
                    horario1.setCargaAcademica(cargaAleatoria);

                    if (horas > 0 && bloque.getBloqueHorario() < 14) {

                        // TODO agregar variable para hora que no queda en pareja y pueda ser agregada
                        // otro día.

                        Periodo siguienteBloque = pr.getOne(bloque.getId() + 1);

                        if (siguienteBloque.getEstatus() && notAlreadyPersisted(cargaAleatoria.getId(), dia.toString(),
                                siguienteBloque.getId())) {
                            Horario horario2 = new Horario();

                            horario2.setDia(dia);
                            horario2.setPeriodo(siguienteBloque);
                            horario2.setCargaAcademica(cargaAleatoria);
                            repo.save(horario2);
                            horas--;
                        }

                    }

                    if (notAlreadyPersisted(cargaAleatoria.getId(), dia.toString(), bloqueParaAsignar.getId())) {
                        horas--;
                        repo.save(horario1);
                    }

                }

            }
        }

    }

    private Periodo getRandomPeriod(List<Periodo> lista, boolean removeElement) {
        int randomIndex = new Random().nextInt(lista.size());
        Periodo elemento = lista.get(randomIndex);
        if (removeElement) {
            lista.remove(randomIndex);
        }
        return elemento;
    }

    private CargaAcademica getRandomCargaAcademica(List<CargaAcademica> lista) {
        int randomIndex = new Random().nextInt(lista.size());
        CargaAcademica elemento = lista.get(randomIndex);
        lista.remove(randomIndex);
        return elemento;
    }

    private boolean notAlreadyPersisted(Long cargaAcademica, String dia, Long periodo) {
        return !repo.getHorarioByDetails(cargaAcademica, dia, periodo).isPresent();
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