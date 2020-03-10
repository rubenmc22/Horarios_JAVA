package com.cpu.sistema_horario_java.app.usuario;

import java.util.List;

public interface UsuarioService {

    Usuario buscar(Long id);

    List<Usuario> listar();

    Usuario guardar(Usuario dto);

    Usuario actualizar(Long id, Usuario dto);

    void eliminar(Long id);

    Usuario login(Usuario usuario);

}