package com.cpu.sistema_horario_java.app.asignatura;

import java.util.List;

public interface AsignaturaService {

    AsignaturaDTO buscar(Long id);

    List<AsignaturaDTO> listar();

    AsignaturaDTO guardar(AsignaturaDTO asignatura);

    AsignaturaDTO reemplazar(Long id, AsignaturaDTO asignatura);

    void eliminar(Long id);

}