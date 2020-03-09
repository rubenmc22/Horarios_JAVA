package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.horario.HorarioDTO;
import com.cpu.sistema_horario_java.app.horario.HorarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/horarios")
public class HorarioController {

    @Autowired
    private HorarioService service;


    @GetMapping("/{id}")
    public HorarioDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping
    public List<HorarioDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public HorarioDTO guardar(@RequestBody HorarioDTO dto) {
         return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public HorarioDTO replaceEmployee(@PathVariable Long id, @RequestBody HorarioDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
