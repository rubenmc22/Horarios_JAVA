package com.cpu.sistema_horario_java.app.materia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    public Optional<MateriaDTO> findByNombre(String nombre);
}