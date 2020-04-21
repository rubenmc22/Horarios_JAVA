package com.cpu.sistema_horario_java.app.asignaturas;

import java.util.List;

public interface AsignaturaService {

    AsignaturaDTO buscar(Long id);

    List<AsignaturaDTO> listar();

    AsignaturaDTO guardar(AsignaturaDTO asignatura);

    AsignaturaDTO reemplazar(Long id, AsignaturaDTO asignatura);

    void eliminar();
    void eliminar(Long id);

}