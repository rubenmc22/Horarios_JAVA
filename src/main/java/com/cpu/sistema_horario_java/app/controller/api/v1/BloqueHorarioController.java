package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.horas.BloqueHorario;
import com.cpu.sistema_horario_java.app.horas.BloqueHorarioService;
import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;
import com.cpu.sistema_horario_java.app.util.types.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BloqueHorarioController {

    @Autowired
    private BloqueHorarioService service;

    @GetMapping("/api/v1/horas/curso/{idCurso}/dia/{dia}")
    public List<BloqueHorario> horasLibresPorCursoPorDia(@PathVariable("idCurso") Long idCurso,
            @PathVariable("dia") Integer dia) {
        return service.horasLibresPorCursoPorDia(idCurso, dia);
    }

    @GetMapping("/api/v1/horas/docente/{idDocente}/dia/{dia}")
    public List<BloqueHorario> horasLibresPorDocentePorDia(@PathVariable("idDocente") Long idDocente,
            @PathVariable("dia") Integer dia) {
        return service.horasLibresPorDocentePorDia(idDocente, dia);
    }

    @GetMapping("/api/v1/horas")
    public List<BloqueHorario> buscar() {
        return service.listar();
    }

    @GetMapping("/api/v1/horas/{id}")
    public BloqueHorario buscar(Long id) {
        return service.buscar(id);
    }

    @PostMapping("/api/v1/horas")
    public BloqueHorario guardar(@RequestBody BloqueHorario bloqueHorario) {
        return service.guardar(bloqueHorario);
    }

    @PutMapping("/api/v1/horas/{id}")
    public BloqueHorario reemplazar(@PathVariable Long id, @RequestBody BloqueHorario bloqueHorario) {
        return service.reemplazar(id, bloqueHorario);
    }

    @DeleteMapping("/api/v1/horas")
    public ResponseEntity<?> eliminar() {
        service.eliminar();
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/api/v1/horas/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
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
