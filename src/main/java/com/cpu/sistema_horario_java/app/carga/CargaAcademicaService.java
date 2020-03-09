package com.cpu.sistema_horario_java.app.carga;

import java.util.List;

public interface CargaAcademicaService {

    CargaAcademicaDTO buscar(Long id);

    List<CargaAcademicaDTO> listar();

    CargaAcademicaDTO guardar(CargaAcademicaDTO dto);

    CargaAcademicaDTO actualizar(Long id, CargaAcademicaDTO dto);

    void eliminar(Long id);

}