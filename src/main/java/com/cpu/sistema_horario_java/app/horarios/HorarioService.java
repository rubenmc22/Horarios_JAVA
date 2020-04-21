package com.cpu.sistema_horario_java.app.horarios;

import java.util.List;

import com.cpu.sistema_horario_java.app.cargas.CargaAcademica;

public interface HorarioService {

    HorarioDTO buscar(Long id);

    List<HorarioDTO> listar();

    List<HorarioDTO> horariosPorCurso(Long id);

    List<HorarioDTO> horariosPorDocente(Long id);

    HorarioDTO guardar(HorarioDTO dto);

    HorarioDTO actualizar(Long id, HorarioDTO dto);

    void generarHorarios();

    void generarHorariosPorCurso(Long id);

    void reservar(Integer dia, Long bloqueHorario, CargaAcademica cargaAcademica);

    void eliminarTodo();

    void eliminar(Long id);

}