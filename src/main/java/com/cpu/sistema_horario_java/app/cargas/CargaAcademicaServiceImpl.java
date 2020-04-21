package com.cpu.sistema_horario_java.app.cargas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.types.Estatus;
import com.cpu.sistema_horario_java.app.cursos.Curso;
import com.cpu.sistema_horario_java.app.cursos.CursoRepository;
import com.cpu.sistema_horario_java.app.horarios.HorarioRepository;
import com.cpu.sistema_horario_java.app.horarios.HorarioService;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.CURSO;
import static com.cpu.sistema_horario_java.app.util.types.EntityType.CARGA_ACADEMICA;
import static com.cpu.sistema_horario_java.app.util.types.EntityType.RESERVA;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_EXCEPTION;;

@Slf4j
@Service
public class CargaAcademicaServiceImpl implements CargaAcademicaService {

    @Autowired
    CargaAcademicaRepository repo;

    @Autowired
    CursoRepository cRepo;

    @Autowired
    HorarioRepository hr;

    @Autowired
    HorarioService hs;

    @Autowired
    CargaAcademicaMapper mapper;

    @Override
    public CargaAcademicaDTO buscar(Long id) {
        Optional<CargaAcademica> model = repo.findById(id);

        if (model.isPresent()) {
            return mapper.toDTO(model.get());
        }
        throw exception(CARGA_ACADEMICA, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<CargaAcademicaDTO> listar() {
        return repo.findAll().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
    }

    @Override
    public CargaAcademicaDTO guardar(CargaAcademicaDTO dto) {
        // TODO validate existence before save

        List<Reserva> reservas = dto.getReservas();
        CargaAcademica carga = mapper.toModel(dto);

        if (reservas.size() > 0) {
            carga = reservar(reservas, carga);
        }

        return mapper.toDTO(repo.save(carga));
        // throw exception(CARGA_ACADEMICA, DUPLICATE_ENTITY, dto.toString());
    }

    @Override
    public CargaAcademicaDTO actualizar(Long id, CargaAcademicaDTO dto) {

        Optional<CargaAcademica> holder = repo.findById(id);
        CargaAcademica carga;

        if (holder.isPresent()) {
            List<Reserva> reservas = dto.getReservas();
            carga = mapper.toModel(dto, holder.get());

            if (reservas.size() > 0) {
                carga = reservar(reservas, carga);
            }
            return mapper.toDTO(repo.save(carga));
        }
        throw exception(CARGA_ACADEMICA, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public CargaAcademica reservar(List<Reserva> reservas, CargaAcademica carga) {
        log.info("Datos para reserva de horas: " + reservas);

        if (reservas.size() > carga.getHoras()) {
            throw exception(RESERVA, ENTITY_EXCEPTION, Integer.toString(reservas.size()));
        }

        for (Reserva reserva : reservas) {
            hs.reservar(reserva.getDia(), reserva.getBloqueHorario(), carga);
        }

        if (reservas.size() == carga.getHoras()) {
            carga.setEstatus(Estatus.PROGRAMADA);
        } else {
            carga.setEstatus(Estatus.RESERVADA);
        }

        return carga;
    }

    @Override
    public void eliminar(Long id) {
        Optional<CargaAcademica> model = repo.findById(id);

        if (model.isPresent()) {
            hr.deleteAll();
            repo.delete(model.get());
        } else {
            throw exception(CARGA_ACADEMICA, ENTITY_NOT_FOUND, id.toString());
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
    public List<CargaAcademicaDTO> cargasPorCurso(Long id) {

        Optional<Curso> curso = cRepo.findById(id);

        if (curso.isPresent()) {
            Optional<List<CargaAcademica>> cargasPorCurso = repo.cargasPorCurso(curso.get());

            if (cargasPorCurso.isPresent()) {
                return cargasPorCurso.get().stream().map(model -> mapper.toDTO(model)).collect(Collectors.toList());
            } else {
                return new ArrayList<>();
            }
        } else {
            throw exception(CURSO, ENTITY_NOT_FOUND, id.toString());
        }
    }
}