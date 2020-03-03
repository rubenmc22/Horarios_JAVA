package com.cpu.sistema_horario_java.app.materia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    public MateriaDTO findByNombe(String nombre);
}