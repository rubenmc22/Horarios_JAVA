package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.horario.HorarioDTO;
import com.cpu.sistema_horario_java.app.horario.HorarioModelAssembler;
import com.cpu.sistema_horario_java.app.horario.HorarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/horarios")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @Autowired
    private HorarioModelAssembler assembler;

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
