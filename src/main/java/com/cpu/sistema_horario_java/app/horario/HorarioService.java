package com.cpu.sistema_horario_java.app.horario;

import java.util.List;

public interface HorarioService {

    HorarioDTO buscar(Long id);

    List<HorarioDTO> listar();

    HorarioDTO guardar(HorarioDTO dto);

    HorarioDTO actualizar(Long id, HorarioDTO dto);

    void generar();

    void eliminar(Long id);

}