package com.cpu.sistema_horario_java.app.materia;

import java.util.Set;

public interface MateriaService {

    MateriaDTO buscar(Long id);

    Set<MateriaDTO> listar();

    MateriaDTO guardar(MateriaDTO materia);

    MateriaDTO actualizar(Long id, MateriaDTO materia);

    void eliminar(Long id);

}