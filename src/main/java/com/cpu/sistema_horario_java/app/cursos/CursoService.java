package com.cpu.sistema_horario_java.app.cursos;

import java.util.List;
import java.util.Set;

import com.cpu.sistema_horario_java.app.util.types.Dia;

// import com.cpu.sistema_horario_java.app.util.exception.SystemExceptionServiceInterface;

//TODO Extends SystemExceptionServiceInterface. As well as all service classess.
public interface CursoService /* extends SystemExceptionServiceInterface */ {

    CursoDTO buscar(Long id);

    List<CursoDTO> listar();

    CursoDTO guardar(CursoDTO dto);

    CursoDTO actualizar(Long id, CursoDTO dto);

    void eliminar();

    void eliminar(Long id);

    Set<Dia> diasDisponibles(Long id);

}