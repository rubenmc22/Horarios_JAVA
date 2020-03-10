package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.usuario.Usuario;
import com.cpu.sistema_horario_java.app.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/api/v1/usuarios/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping("/api/v1/usuarios")
    public List<Usuario> listar() {
        return service.listar();
    }

    @PostMapping("/api/v1/usuarios")
    public Usuario guardar(@RequestBody Usuario dto) {
        return service.guardar(dto);
    }

    @PutMapping("/api/v1/usuarios/{id}")
    public Usuario replaceEmployee(@PathVariable Long id, @RequestBody Usuario dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/api/v1/usuarios/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/v1/login")
    public Usuario login(@RequestBody Usuario dto) {
        return service.login(dto);
    }

}
