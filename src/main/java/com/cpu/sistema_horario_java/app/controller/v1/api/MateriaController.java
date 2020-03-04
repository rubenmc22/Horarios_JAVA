package com.cpu.sistema_horario_java.app.controller.v1.api;

import java.util.List;

import com.cpu.sistema_horario_java.app.materia.MateriaDTO;
import com.cpu.sistema_horario_java.app.materia.MateriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/materias")
public class MateriaController {

    @Autowired
    private MateriaService service;

    @GetMapping("/{id}")
    MateriaDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping
    List<MateriaDTO> listar() {
        return service.listar();
    }

    @PostMapping
    MateriaDTO guardar(@RequestBody MateriaDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    MateriaDTO replaceEmployee(@PathVariable Long id, @RequestBody MateriaDTO materia) {
        return service.actualizar(id, materia);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);
    }

}