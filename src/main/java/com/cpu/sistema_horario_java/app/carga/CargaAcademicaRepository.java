package com.cpu.sistema_horario_java.app.carga;

import java.util.List;
import java.util.Optional;

import com.cpu.sistema_horario_java.app.curso.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CargaAcademicaRepository extends JpaRepository<CargaAcademica, Long> {

    @Query(value = "SELECT c FROM CargaAcademica c WHERE c.curso = :curso")
    public Optional<List<CargaAcademica>> cargasPorCurso(@Param("curso") Curso curso);

}