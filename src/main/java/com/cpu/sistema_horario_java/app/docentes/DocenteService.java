package com.cpu.sistema_horario_java.app.docentes;

import java.util.List;

public interface DocenteService {

    DocenteDTO buscar(Long id);

    List<DocenteDTO> listar();

    DocenteDTO guardar(DocenteDTO dto);

    DocenteDTO actualizar(Long id, DocenteDTO dto);

    void eliminar();

    void eliminar(Long id);

}