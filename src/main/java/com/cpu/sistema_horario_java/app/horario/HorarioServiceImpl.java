package com.cpu.sistema_horario_java.app.horario;

import com.cpu.sistema_horario_java.app.util.types.Dia;
import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.HORARIO;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;
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

        final List<Periodo> periodos = pr.findAll();
        List<Dia> dias = new ArrayList<>();
        List<CargaAcademica> cargasAcademicas = car.findAll();

        for (Dia dia : Dia.values()) {
            if (dia.getNumeroDia() < 6) {
                dias.add(dia);
            }
        }

        while (dias.size() > 0) {
            List<Periodo> controlPeriodos = periodos;
            Dia alDia = getRandomDia(dias);

            Periodo alPer = getRandomPeriod(controlPeriodos);
            Optional<Periodo> siguiente = Optional.empty();
            CargaAcademica alCar = getRandomCargaAcademica(cargasAcademicas);
            Integer horasCarga = alCar.getHoras();

            if (cargasAcademicas.size() <= 0) {
                break;
            }

            if (!alPer.getEstatus()) {
                dias.add(alDia);
                periodos.remove(alPer);
                cargasAcademicas.add(alCar);
                continue;
            }

            while (horasCarga >= 1) {
                if (horasCarga > 1 && alPer.getBloqueHorario() < 14) {
                    siguiente = pr.findById(alPer.getId() + 1L);

                    if (siguiente.isPresent()) {

                        // TODO REPLACE TRY-CATCH BY QUERY-VALIDATE FUNCTION
                        try {
                            if (validate(alCar.getId(), alDia, siguiente.get().getId())) {
                                horasCarga--;
                                log.info("GUARDANDO HORARIO 2: " + repo.save(Horario.builder().dia(alDia)
                                        .periodo(siguiente.get()).cargaAcademica(alCar).build()));
                            }
                        } catch (Exception e) {
                            // TODO VALIDATE IF ITS SAFE TO REINSERT VALUES
                            log.info("ERROR DE LLAVE DUPLICADA AL GUARDAR.");
                            break;
                        }

                    }
                }

                // TODO REPLACE TRY-CATCH BY QUERY-VALIDATE FUNCTION
                try {
                    if (validate(alCar.getId(), alDia, siguiente.get().getId())) {
                        horasCarga--;
                        log.info("GUARDANDO HORARIO 2: "
                                + repo.save(Horario.builder().dia(alDia).periodo(alPer).cargaAcademica(alCar).build()));
                    }
                } catch (Exception e) {
                    // TODO VALIDATE IF ITS SAFE TO REINSERT VALUES
                    log.info("ERROR DE LLAVE DUPLICADA AL GUARDAR.");
                    break;
                }

            }

        }

    }

    private Periodo getRandomPeriod(List<Periodo> lista) {
        int randomIndex = new Random().nextInt(lista.size());
        Periodo elemento = lista.get(randomIndex);
        lista.remove(randomIndex);
        return elemento;
    }

    private CargaAcademica getRandomCargaAcademica(List<CargaAcademica> lista) {
        int randomIndex = new Random().nextInt(lista.size());
        CargaAcademica elemento = lista.get(randomIndex);
        lista.remove(randomIndex);
        return elemento;
    }

    private Dia getRandomDia(List<Dia> lista) {
        int randomIndex = new Random().nextInt(lista.size());
        Dia elemento = lista.get(randomIndex);
        lista.remove(randomIndex);
        return elemento;
    }

    private boolean validate(Long cargaAcademica, Dia dia, Long periodo) {
        // return !repo.getHorarioByDetails(cargaAcademica, dia, periodo).isPresent();
        return true;
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