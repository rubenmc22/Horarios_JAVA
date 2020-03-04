package com.cpu.sistema_horario_java.app.profesor;

import java.util.List;

public interface ProfesorService {

    ProfesorDTO buscar(Long id);

    List<ProfesorDTO> listar();

    ProfesorDTO guardar(ProfesorDTO dto);

    ProfesorDTO actualizar(Long id, ProfesorDTO dto);

    void eliminar(Long id);

}