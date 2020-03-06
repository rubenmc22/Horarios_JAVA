package com.cpu.sistema_horario_java.app.curso;

import java.util.List;

public interface CursoService {

    CursoDTO buscar(Long id);

    List<CursoDTO> listar();

    CursoDTO guardar(CursoDTO dto);

    CursoDTO actualizar(Long id, CursoDTO dto);

    void eliminar(Long id);

}