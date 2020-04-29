package com.cpu.sistema_horario_java.app.cargas;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.cpu.sistema_horario_java.app.asignaturas.Asignatura;
import com.cpu.sistema_horario_java.app.cursos.Curso;
import com.cpu.sistema_horario_java.app.docentes.Docente;
import com.cpu.sistema_horario_java.app.util.types.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CargaAcademicaRepository extends JpaRepository<CargaAcademica, Long> {

    @Query("SELECT c FROM CargaAcademica c WHERE c.curso = :curso")
    public Optional<List<CargaAcademica>> cargasPorCurso(@Param("curso") Curso curso);

    @Modifying
    @Transactional
    @Query("DELETE CargaAcademica c WHERE c.asignatura = :asignatura")
    public void eliminarCargasPorAsignatura(@Param("asignatura") Asignatura asignatura);

    @Modifying
    @Transactional
    @Query("DELETE CargaAcademica c WHERE c.curso = :curso")
    public void eliminarCargasPorCurso(@Param("curso") Curso curso);

    @Modifying
    @Transactional
    @Query("DELETE CargaAcademica c WHERE c.docente = :docente")
    public void eliminarCargasPorDocente(@Param("docente") Docente docente);

    @Query("SELECT c FROM CargaAcademica c WHERE c.estatus = :estatus")
    public Optional<List<CargaAcademica>> cargasPorEstatus(@Param("estatus") Estatus estatus);

    @Query(value = "SELECT * FROM CARGAS_ACADEMICAS C WHERE C.ESTATUS <> 'PROGRAMADA'", nativeQuery = true)
    public Optional<List<CargaAcademica>> cargasPendientes();

    @Query(value = "SELECT * FROM CARGAS_ACADEMICAS C WHERE C.ID_CURSO = :curso AND C.ESTATUS <> 'PROGRAMADA'", nativeQuery = true)
    public Optional<List<CargaAcademica>> cargasPendientesPorCurso(@Param("curso") Long curso);

    @Query("SELECT c FROM CargaAcademica c WHERE c.curso = :curso AND c.estatus = :estatus")
    public Optional<List<CargaAcademica>> cargasPorCursoPorEstatus(@Param("curso") Curso curso, @Param("estatus") Estatus estatus);

    @Modifying
    @Transactional
    @Query("UPDATE CargaAcademica c SET c.estatus = :estatus")
    public void actualizarEstatus(@Param("estatus") final Estatus estatus);

    @Modifying
    @Transactional
    @Query("UPDATE CargaAcademica c SET c.estatus = :estatus WHERE c.curso = :curso")
    public void actualizarEstatusPorCurso(@Param("estatus") final Estatus estatus, @Param("curso") Curso curso);

}