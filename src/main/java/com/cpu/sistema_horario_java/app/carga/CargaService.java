package com.cpu.sistema_horario_java.app.carga;

import java.util.List;

public interface CargaService {

    CargaDTO buscar(Long id);

    List<CargaDTO> listar();

    CargaDTO guardar(CargaDTO dto);

    CargaDTO actualizar(Long id, CargaDTO dto);

    void eliminar(Long id);

}