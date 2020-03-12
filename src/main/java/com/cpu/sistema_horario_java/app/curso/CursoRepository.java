package com.cpu.sistema_horario_java.app.curso;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Optional<CursoDTO> findByNombre(String nombre);

    @Modifying
    @Transactional
    @Query("DELETE CargaAcademica c WHERE c.curso = :curso")
    public void deleteAllCargaAssociatedToCurso(@Param("curso") Curso curso);

}