package com.cpu.sistema_horario_java.app.docente;

import java.util.List;

public interface DocenteService {

    DocenteDTO buscar(Long id);

    List<DocenteDTO> listar();

    DocenteDTO guardar(DocenteDTO dto);

    DocenteDTO actualizar(Long id, DocenteDTO dto);

    void eliminar(Long id);

}