package com.cpu.sistema_horario_java.app.horas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.BLOQUE_HORARIO;
import static com.cpu.sistema_horario_java.app.util.types.EntityType.DIA;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

@Slf4j
@Service
public class BloqueHorarioServiceImpl implements BloqueHorarioService {

    @Autowired
    BloqueHorarioRepository repo;

    @Override
    public BloqueHorario buscar(Long id) {
        Optional<BloqueHorario> bloque = repo.findById(id);

        if (bloque.isPresent()) {
            return bloque.get();
        }
        throw exception(BLOQUE_HORARIO, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<BloqueHorario> horasLibresPorCursoPorDia(Long idCurso, Integer dia) {
        String _dia = integerToDiaString(dia);
        if (_dia.equals("")) {
            throw exception(DIA, ENTITY_NOT_FOUND, dia.toString());
        }
        return repo.horasLibresPorCursoPorDia(_dia, idCurso);
    }

    @Override
    public List<BloqueHorario> listar() {
        return repo.findAll();
    }

    @Override
    public BloqueHorario guardar(BloqueHorario bloque) {
        // TODO implement PRE-SAVE validator
        // Optional<BloqueHorario> bloque = repo.findByNombre(dto.getNombre());

        // if (!bloque.isPresent()) {
        return repo.save(bloque);
        // }

        // throw exception(BloqueHorario, DUPLICATE_ENTITY, dto.getNombre());
    }

    @Override
    public BloqueHorario reemplazar(Long id, BloqueHorario bloqueNew) {

        Optional<BloqueHorario> bloqueOld = repo.findById(id);

        if (bloqueOld.isPresent()) {
            bloqueNew.setId(bloqueOld.get().getId());
            return repo.save(bloqueNew);
        } else {
            throw exception(BLOQUE_HORARIO, ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    public void eliminar() {
        List<BloqueHorario> bloques = repo.findAll();

        log.info("ELIMINAR BLOQUES HORARIOS - BUSCANDO");
        if (bloques.size() > 0) {
            repo.deleteAll();
            log.info("BLOQUES HORARIOS ELIMINANDOS");
        } else {
            log.info("NO HAY BLOQUES HORARIOS PARA ELIMINAR");
        }
    }

    @Override
    public void eliminar(final Long ID) {
        Optional<BloqueHorario> bloque = repo.findById(ID);

        log.info("ELIMINAR BLOQUE HORARIO - ID: " + ID + " BUSCANDO");
        if (bloque.isPresent()) {
            log.info("ENCONTRADO - ELIMINANDO");
            repo.delete(bloque.get());
            log.info("BLOQUE HORARIO - ID: " + ID + " ELIMINADO");
        } else {
            log.info("BLOQUE HORARIO " + ID + " NO ENCONTRADO");
            throw exception(BLOQUE_HORARIO, ENTITY_NOT_FOUND, ID.toString());
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
    public List<BloqueHorario> horasLibresPorDocentePorDia(Long idDocente, Integer dia) {

        String _dia = integerToDiaString(dia);
        if (_dia.equals("")) {
            throw exception(DIA, ENTITY_NOT_FOUND, dia.toString());

        }
        return repo.horasLibresPorDocentePorDia(_dia, idDocente);
    }
}