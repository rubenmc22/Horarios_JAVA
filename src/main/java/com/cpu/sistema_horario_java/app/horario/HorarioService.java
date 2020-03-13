package com.cpu.sistema_horario_java.app.horario;

import java.util.List;

public interface HorarioService {

    HorarioDTO buscar(Long id);

    List<HorarioDTO> listar();

    List<HorarioDTO> horariosPorCurso(Long id);

    HorarioDTO guardar(HorarioDTO dto);

    HorarioDTO actualizar(Long id, HorarioDTO dto);

    void generarHorarios();

    void generarHorariosPorCurso(Long id);

    void eliminar(Long id);

}