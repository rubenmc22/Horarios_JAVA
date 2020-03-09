package com.cpu.sistema_horario_java.app.curso;

import java.util.List;
import java.util.Optional;

import com.cpu.sistema_horario_java.app.util.types.Dia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Optional<CursoDTO> findByNombre(String nombre);

    public List<Curso> findByDia(Dia dia);
}