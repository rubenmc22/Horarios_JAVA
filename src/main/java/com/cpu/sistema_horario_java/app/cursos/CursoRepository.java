package com.cpu.sistema_horario_java.app.cursos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Optional<Curso> findByNombre(String nombre);

}