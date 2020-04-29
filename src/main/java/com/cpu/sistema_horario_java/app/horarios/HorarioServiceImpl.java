package com.cpu.sistema_horario_java.app.horarios;

import com.cpu.sistema_horario_java.app.util.types.Dia;
import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.types.Estatus;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.*;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.cpu.sistema_horario_java.app.asignaturas.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.cargas.CargaAcademica;
import com.cpu.sistema_horario_java.app.cargas.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.cursos.Curso;
import com.cpu.sistema_horario_java.app.cursos.CursoRepository;
import com.cpu.sistema_horario_java.app.docentes.Docente;
import com.cpu.sistema_horario_java.app.docentes.DocenteRepository;
import com.cpu.sistema_horario_java.app.horas.BloqueHorario;
import com.cpu.sistema_horario_java.app.horas.BloqueHorarioRepository;
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
    BloqueHorarioRepository br;

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

            Optional<List<Horario>> horarios = repo.horariosPorCurso(id);
            if (horarios.isPresent()) {
                return horarios.get().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }
        } else {
            throw exception(CURSO, ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    public List<HorarioDTO> horariosPorDocente(Long id) {
        final Optional<Docente> docente = dr.findById(id);
        if (docente.isPresent()) {

            Optional<List<Horario>> horarios = repo.horariosPorDocente(id);
            if (horarios.isPresent()) {
                return horarios.get().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }
        } else {
            throw exception(DOCENTE, ENTITY_NOT_FOUND, id.toString());
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
    public void eliminarTodo() {
        car.actualizarEstatus(Estatus.PENDIENTE);
        repo.deleteAll();
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
    public void eliminarPorCurso(Long id) {
        final Optional<Curso> curso = cr.findById(id);
        if (curso.isPresent()) {

            Optional<List<Horario>> horarios = repo.horariosPorCurso(id);
            if (horarios.isPresent()) {
                repo.deleteInBatch(horarios.get());
            }
        } else {
            throw exception(CURSO, ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    @Transactional
    public void generarHorarios() {
        // se pasa el valor null como id de curso para activar la generación de los
        // horarios de todos los cursos
        generarHorariosPorCurso(null);
    }

    @Override
    @Transactional
    public void generarHorariosPorCurso(Long id) {
        log.info("INICIADA GENERACIÓN DE HORARIOS");

        int tries = 0;// contador colocado para evitar que el proceso entre en un loop infinito
        List<Dia> dias;
        List<CargaAcademica> cargasAcademicas = new ArrayList<>();
        List<BloqueHorario> bloquesAcademicos = new ArrayList<>();

        // elección de los datos de CargaAcademica a usar para generar horarios

        // id = null -> generar los horarios para todas las cargas académicas no
        // programadas
        if (id == null) {
            Optional<List<CargaAcademica>> cargasPendientes = car.cargasPendientes();
            if (cargasPendientes.isPresent()) {
                cargasAcademicas = cargasPendientes.get();
            } else {
                throw exception(CARGA_ACADEMICA, ENTITY_NOT_FOUND, "none");
            }
        } else {
            // id = value -> generar los horarios para todas las cargas académicas asociadas
            // a un curso en particular
            Optional<Curso> curso = cr.findById(id);
            if (curso.isPresent()) {
                Optional<List<CargaAcademica>> cargas = car.cargasPendientesPorCurso(curso.get().getId());
                if (cargas.isPresent()) {
                    cargasAcademicas = cargas.get();
                } else {
                    throw exception(CURSO_CARGA_ACADEMICA, ENTITY_NOT_FOUND, id.toString());
                }
            } else {
                throw exception(CURSO, ENTITY_NOT_FOUND, id.toString());
            }
        }

        // Se generarán tantos horarios como horas indique la carga académica en parejas
        // de bloques horarios. n° horarios = horas % 2
        for (CargaAcademica cargaAcademica : cargasAcademicas) {
            log.info("\n***********************************************");
            log.info("CARGA ACADEMICA ID: " + cargaAcademica.getId());

            // lista usada para evitar programar actividades el mismo día en bloques
            // horarios diferentes.
            List<Dia> diasYaProgramados = new ArrayList<>();

            // si la carga académica posee horas ya asignadas estas deben ser descontadas
            // del
            // total de horas
            Integer horasYaAsignadas = cargaAcademica.getEstatus().equals(Estatus.RESERVADA)
                    ? repo.cantidadHorariosPorCarga(cargaAcademica)
                    : 0;
            Integer horasTotales = cargaAcademica.getHoras();
            Integer horas = horasYaAsignadas == 0 ? horasTotales : horasTotales - horasYaAsignadas;

            // lista de días habilitados según el curso
            dias = ListUtil.toList(cargaAcademica.getCurso().getDias());
            log.info("dias: " + dias);

            // día tentativo para el horario a generar elegido aleatoriamente entre los días
            // habilitados para el curso ignorando los otros días ya programados para esta
            // carga académica
            Dia diaTentativo = (Dia) ListUtil.getRandomElement(dias, diasYaProgramados);
            log.info("diaTentativo: " + diaTentativo);

            // bloques horarios disponibles por docente para un día determinado
            bloquesAcademicos = br.horasLibresPorDocentePorDia(diaTentativo.toString(),
                    cargaAcademica.getDocente().getId());

            // horas > 0 -> quedan horas pendientes por programar
            while (horas > 0) {
                log.info("horas pendientes: " + horas);
                Horario horario;
                BloqueHorario bloqueHorarioTentativo;

                // bloque colocado para manejar las horas solitarias producto de la programación
                // de horas impares
                if (horas.equals(1)) {
                    horario = new Horario();

                    /************************************************** */
                    diaTentativo = (Dia) ListUtil.getRandomElement(dias, diasYaProgramados);

                    // los bloques académicos disponibles deben ser ajustados cada vez que se ajuste
                    // el día tentativo
                    bloquesAcademicos = br.horasLibresPorDocentePorDia(diaTentativo.toString(),
                            cargaAcademica.getDocente().getId());
                    /*************************************************** */

                    bloqueHorarioTentativo = (BloqueHorario) ListUtil.getRandomElement(bloquesAcademicos);

                    // si la combinación de dia, bloque horario y carga académica ya se encuentra
                    // registrada tanto el día como el bloque horario se modifican de manera
                    // aleatoria
                    while (alreadyPersisted(diaTentativo.toString(), bloqueHorarioTentativo.getId(),
                            cargaAcademica.getCurso().getId())) {

                        log.info("shuffling data and trying again.");

                        // bloque introducido para evitar los loops infinitos que se pueden producir si
                        // se genera una excesiva cantidad de cargas académicas y se saturan los
                        // horarios de todos los cursos todos los días
                        // TODO add appropriate exception handling and messaging
                        if (tries > 200) {
                            break;
                        }

                        diaTentativo = (Dia) ListUtil.getRandomElement(dias, diasYaProgramados);

                        // los bloques académicos disponibles deben ser ajustados cada vez que se ajuste
                        // el día tentativo
                        bloquesAcademicos = br.horasLibresPorDocentePorDia(diaTentativo.toString(),
                                cargaAcademica.getDocente().getId());
                        bloqueHorarioTentativo = (BloqueHorario) ListUtil.getRandomElement(bloquesAcademicos);
                        tries++;
                    }

                    horario.setCargaAcademica(cargaAcademica);
                    horario.setDia(diaTentativo);
                    horario.setBloqueHorario(bloqueHorarioTentativo);

                    log.info("Guardando horario: " + repo.save(horario));

                    // actualizando variables de control
                    horas--;
                    diasYaProgramados.add(diaTentativo);
                } else {

                    // día tentativo para el horario a generar es elegido aleatoriamente entre los
                    // días habilitados para el curso, ignorando los otros días ya programados para
                    // esta carga académica
                    diaTentativo = (Dia) ListUtil.getRandomElement(dias, diasYaProgramados);

                    // bloques disponibles se ajustan al día tentativo
                    bloquesAcademicos = br.horasLibresPorDocentePorDia(diaTentativo.toString(),
                            cargaAcademica.getDocente().getId());

                    // mientras la cantidad de bloques disponibles sea menor a la mínima necesaria
                    // (2) se debe seguir
                    while (bloquesAcademicos.size() < 2) {
                        diaTentativo = (Dia) ListUtil.getRandomElement(dias, diasYaProgramados);

                        // bloques disponibles se ajustan al día tentativo
                        bloquesAcademicos = br.horasLibresPorDocentePorDia(diaTentativo.toString(),
                                cargaAcademica.getDocente().getId());
                    }

                    // a diferencia del manejo de horas individuales el comportamiento por defecto
                    // para la programación de horas > 1 es la asignación de bloques horarios en
                    // parejas consecutivas
                    List<BloqueHorario> parBloqueTentativo = getValidBloqueHorarioPair(bloquesAcademicos);

                    // si la combinación de dia, par bloque horario y carga académica ya se
                    // encuentra registrada tanto el día como el par de bloques horarios se
                    // modifican de manera aleatoria
                    while (parBloqueTentativo.size() < 2
                            || alreadyPersisted(diaTentativo.toString(), parBloqueTentativo.get(0).getId(),
                                    cargaAcademica.getCurso().getId())
                            || alreadyPersisted(diaTentativo.toString(), parBloqueTentativo.get(1).getId(),
                                    cargaAcademica.getCurso().getId())) {
                        log.info("shuffling data and trying again.");

                        // bloque introducido para evitar los loops infinitos que se pueden producir si
                        // se genera una excesiva cantidad de cargas académicas y se saturan los
                        // horarios de todos los cursos todos los días
                        // TODO add appropriate exception handling and messagin
                        if (tries > 200) {
                            break;
                        }

                        diaTentativo = (Dia) ListUtil.getRandomElement(dias, diasYaProgramados);

                        // bloques disponibles se ajustan al día tentativo
                        bloquesAcademicos = br.horasLibresPorDocentePorDia(diaTentativo.toString(),
                                cargaAcademica.getDocente().getId());

                        // elección de un par de bloques horarios disponibles (estatus = true) y
                        // consecutivos
                        parBloqueTentativo = getValidBloqueHorarioPair(bloquesAcademicos);
                        tries++;
                    }

                    // por cada bloque se genera un horario con la misma carga académica y día
                    for (BloqueHorario bloque : parBloqueTentativo) {
                        horario = new Horario();

                        bloqueHorarioTentativo = bloque;
                        horario.setCargaAcademica(cargaAcademica);
                        horario.setDia(diaTentativo);
                        horario.setBloqueHorario(bloqueHorarioTentativo);

                        // TODO local variable to show data for debugging purpose only - should be
                        // removed when changing to productio mode
                        Horario save = repo.save(horario);

                        log.info("Datos almacenados: dia: " + save.getDia() + " - bloque: "
                                + save.getBloqueHorario().getId() + " - asignatura: "
                                + save.getCargaAcademica().getAsignatura().getNombre());
                        // actualizando variables de control
                        horas--;
                    }
                    diasYaProgramados.add(diaTentativo);
                }
            }

            // al programar todas las horas de la carga académica se ajusta el estatus de la
            // misma
            cargaAcademica.setEstatus(Estatus.PROGRAMADA);
            car.save(cargaAcademica);
        }
        log.info("FINALIZADA GENERACIÓN DE HORARIOS");

    }

    private List<BloqueHorario> getValidBloqueHorarioPair(List<BloqueHorario> bloquesAcademicos) {

        List<BloqueHorario> par = new ArrayList<>();
        List<BloqueHorario> bloquesConsecutivos = getBloquesConsecutivos(bloquesAcademicos);
        if (bloquesConsecutivos.size() < 2) {
            return par;
        }
        BloqueHorario bloque = (BloqueHorario) ListUtil.getRandomElement(bloquesConsecutivos);
        BloqueHorario siguienteBloque;

        if (ListUtil.isFirst(bloquesConsecutivos, bloque)) {
            siguienteBloque = (BloqueHorario) ListUtil.getNextElement(bloquesConsecutivos, bloque);
        } else if (ListUtil.isLast(bloquesConsecutivos, bloque)) {
            siguienteBloque = (BloqueHorario) ListUtil.getPreviousElement(bloquesConsecutivos, bloque);
        } else {
            BloqueHorario bloquePrevio = (BloqueHorario) ListUtil.getPreviousElement(bloquesConsecutivos, bloque);
            BloqueHorario bloqueSiguiente = (BloqueHorario) ListUtil.getNextElement(bloquesConsecutivos, bloque);

            if (bloqueSiguiente.getBloqueHorario() == bloque.getBloqueHorario() + 1) {
                siguienteBloque = bloqueSiguiente;
            } else {
                siguienteBloque = bloquePrevio;
            }
        }

        par.add(bloque);
        par.add(siguienteBloque);

        return par;
    }

    private List<BloqueHorario> getBloquesConsecutivos(List<BloqueHorario> bloquesAcademicos) {
        List<BloqueHorario> bloquesConsecutivos = new ArrayList<>();
        boolean primero = true;
        BloqueHorario bloqueAux = null;

        for (int i = 0; i < bloquesAcademicos.size(); i++) {
            if (primero) {
                primero = false;
                bloquesConsecutivos.add(bloquesAcademicos.get(i));
                continue;
            }

            if (bloquesAcademicos.get(i).getBloqueHorario()
                    .equals(bloquesAcademicos.get(i - 1).getBloqueHorario() + Integer.valueOf(1))) {
                if (bloqueAux != null) {
                    bloquesConsecutivos.add(bloqueAux);
                    bloqueAux = null;
                }
                bloquesConsecutivos.add(bloquesAcademicos.get(i));
            } else {
                bloqueAux = bloquesAcademicos.get(i);
            }
        }
        return bloquesConsecutivos;
    }

    private boolean alreadyPersisted(String dia, Long bloqueHorario, Long curso) {
        log.info("validating persistence of data. dia: " + dia + ", bloque: " + bloqueHorario + ", curso: " + curso);

        boolean present = repo.getHorarioAsignado(dia, bloqueHorario, curso).isPresent();

        log.info("is data already persisted?: " + present);

        return present;
    }

    @Override
    public void reservar(Integer dia, Long bloqueHorario, CargaAcademica cargaAcademica) {
        Dia objDia = null;
        BloqueHorario bloque = null;

        Optional<BloqueHorario> objBLoque = br.findById(bloqueHorario);

        if (objBLoque.isPresent()) {
            bloque = objBLoque.get();
        } else {
            throw exception(BLOQUE_HORARIO, ENTITY_NOT_FOUND, dia.toString());
        }

        try {
            objDia = Dia.values()[dia];
        } catch (Exception e) {
            throw exception(DIA, ENTITY_NOT_FOUND, bloqueHorario.toString());
        }

        repo.save(Horario.builder().dia(objDia).bloqueHorario(bloque).cargaAcademica(cargaAcademica).build());
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