package com.cpu.sistema_horario_java.app.cargas;

import java.util.List;

public interface CargaAcademicaService {

    CargaAcademicaDTO buscar(Long id);

    List<CargaAcademicaDTO> listar();

    List<CargaAcademicaDTO> cargasPorCurso(Long id);

    CargaAcademicaDTO guardar(CargaAcademicaDTO dto);

    CargaAcademicaDTO actualizar(Long id, CargaAcademicaDTO dto);

    CargaAcademica reservar(List<Reserva> reservas, CargaAcademica carga);

    void eliminar(Long id);

}