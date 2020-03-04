package com.cpu.sistema_horario_java.app.materia;

import java.util.List;

public interface MateriaService {

    MateriaDTO buscar(Long id);

    List<MateriaDTO> listar();

    MateriaDTO guardar(MateriaDTO materia);

    MateriaDTO actualizar(Long id, MateriaDTO materia);

    void eliminar(Long id);

}