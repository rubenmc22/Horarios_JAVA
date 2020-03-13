package com.cpu.sistema_horario_java.app.horario;

import com.cpu.sistema_horario_java.app.util.types.Dia;
import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.types.Estatus;
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

    @Transactional
    public void generar() {

        int tries = 0;
        List<Periodo> bloquesAcademicos = pr.periodosParaAsignar();
        List<Dia> dias = new ArrayList<>();

        for (Dia dia : Dia.values()) {
            if (dia.getNumeroDia() < 6) {
                dias.add(dia);
            }
        }

        List<CargaAcademica> cargasAcademicas = car.findAll();

        for (CargaAcademica cargaAcademica : cargasAcademicas) {
            Integer horas = cargaAcademica.getHoras();

            while (horas > 0) {
                Horario horario;
                Dia diaTentativo;
                Periodo periodoTentativo;

                if (horas.equals(1)) {
                    horario = new Horario();
                    diaTentativo = (Dia) getRandomElement(dias);
                    periodoTentativo = (Periodo) getRandomElement(bloquesAcademicos);

                    while (alreadyPersisted(diaTentativo, periodoTentativo)) {
                        if (tries > 200) {
                            break;
                        }

                        diaTentativo = (Dia) getRandomElement(dias);
                        periodoTentativo = (Periodo) getRandomElement(bloquesAcademicos);
                        tries++;
                    }

                    horario.setCargaAcademica(cargaAcademica);
                    horario.setDia(diaTentativo);
                    horario.setPeriodo(periodoTentativo);

                    log.info("Not present - saving entity: " + repo.save(horario));
                    ;
                    horas--;
                }

                diaTentativo = (Dia) getRandomElement(dias);
                List<Periodo> parBloqueTentativo = getValidPeriodoPair(bloquesAcademicos);

                while (alreadyPersisted(diaTentativo, parBloqueTentativo.get(0))
                        || alreadyPersisted(diaTentativo, parBloqueTentativo.get(1))) {
                    if (tries > 200) {
                        break;
                    }

                    diaTentativo = (Dia) getRandomElement(dias);
                    parBloqueTentativo = getValidPeriodoPair(bloquesAcademicos);
                    tries++;
                }

                for (Periodo bloque : parBloqueTentativo) {
                    horario = new Horario();

                    periodoTentativo = bloque;
                    horario.setCargaAcademica(cargaAcademica);
                    horario.setDia(diaTentativo);
                    horario.setPeriodo(periodoTentativo);

                    log.info("Not present - saving entity: " + repo.save(horario));
                    horas--;
                }

            }
            cargaAcademica.setEstatus(Estatus.PROGRAMADA);
            car.save(cargaAcademica);

        }

    }

    private List<Periodo> getValidPeriodoPair(List<Periodo> bloquesAcademicos) {
        List<Periodo> par = new ArrayList<>();
        Periodo bloque = (Periodo) getRandomElement(bloquesAcademicos);
        Periodo siguienteBloque = pr.getOne(bloque.getId() + 1L);

        while (siguienteBloque.getEstatus() == Boolean.FALSE) {
            bloque = (Periodo) getRandomElement(bloquesAcademicos);
            siguienteBloque = pr.getOne(bloque.getId() + 1L);
        }

        par.add(bloque);
        par.add(siguienteBloque);

        return par;
    }

    private <T> Object getRandomElement(List<T> list, Boolean removeFromList) {
        Object element = getRandomElement(list);

        if (removeFromList) {
            list.remove(element);
        }
        return (T) element;
    }

    private <T> Object getRandomElement(List<T> list, Object ignoreThis, Boolean removeFromList) {
        Object element = getRandomElement(list, ignoreThis);

        if (removeFromList) {
            list.remove(element);
        }
        return (T) element;
    }

    private <T> Object getRandomElement(List<T> list, Object ignoreThis) {
        Object element = getRandomElement(list);

        while (element.equals(ignoreThis)) {
            element = getRandomElement(list);
        }
        return (T) element;
    }

    private <T> Object getRandomElement(List<T> list) {
        int randomIndex = new Random().nextInt(list.size());
        Object element = (T) list.get(randomIndex);
        return (T) element;

    }

    private CargaAcademica getRandomCargaAcademica(List<CargaAcademica> lista) {
        int randomIndex = new Random().nextInt(lista.size());
        CargaAcademica elemento = lista.get(randomIndex);
        lista.remove(randomIndex);
        return elemento;
    }

    private boolean notAlreadyPersisted(Dia dia, Periodo periodo) {
        return !repo.getHorarioAsignado(dia, periodo).isPresent();
    }

    private boolean alreadyPersisted(Dia dia, Periodo periodo) {
        return repo.getHorarioAsignado(dia, periodo).isPresent();
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