package com.cpu.sistema_horario_java.app.curso;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Optional<CursoDTO> findByNombre(String nombre);
}