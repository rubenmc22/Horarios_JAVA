package com.cpu.sistema_horario_java.app.horarios;

import java.util.List;
import java.util.Optional;

import com.cpu.sistema_horario_java.app.cargas.CargaAcademica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query(value = "SELECT * FROM HORARIOS H, CARGAS_ACADEMICAS C WHERE H.DIA = :dia AND H.ID_BLOQUE_HORARIO = :bloque AND H.ID_CARGA_ACADEMICA = C.ID AND C.ID_CURSO = :curso", nativeQuery = true)
    public Optional<List<Horario>> getHorarioAsignado(@Param("dia") String dia, @Param("bloque") Long bloque,
            @Param("curso") Long curso);

    @Query(value = "SELECT COUNT(h) FROM Horario h WHERE h.cargaAcademica = :carga")
    public Integer cantidadHorariosPorCarga(@Param("carga") CargaAcademica carga);

    @Query(value = "SELECT * FROM HORARIOS H, CARGAS_ACADEMICAS C WHERE H.ID_CARGA_ACADEMICA = C.ID AND C.ID_ASIGNATURA = :asignatura", nativeQuery = true)
    public Optional<List<Horario>> horariosPorAsignatura(@Param("asignatura") Long asignatura);
    
    @Query(value = "SELECT * FROM HORARIOS H, CARGAS_ACADEMICAS C WHERE H.ID_CARGA_ACADEMICA = C.ID AND C.ID_CURSO = :curso ORDER BY H.DIA, H.ID_BLOQUE_HORARIO", nativeQuery = true)
    public Optional<List<Horario>> horariosPorCurso(@Param("curso") Long curso);

    @Query(value = "SELECT * FROM HORARIOS H, CARGAS_ACADEMICAS C WHERE H.ID_CARGA_ACADEMICA = C.ID AND C.ID_DOCENTE = :docente", nativeQuery = true)
    public Optional<List<Horario>> horariosPorDocente(@Param("docente") Long docente);

    // TODO implement native delete queries
    // @Modifying
    // @Transactional
    // @Query(value = "DELETE FROM HORARIOS H WHERE H.ID_CARGA_ACADEMICA IN (SELECT
    // C.ID FROM CARGAS_ACADEMICAS C WHERE C.ID_ASIGNATURA = :asignatura",
    // nativeQuery = true)
    // public Optional<List<Horario>>
    // eliminarHorariosPorAsignatura(@Param("asignatura") Long asignatura);

    // @Modifying
    // @Transactional
    // @Query(value = "DELETE FROM HORARIOS H WHERE H.ID_CARGA_ACADEMICA IN (SELECT
    // C.ID FROM CARGAS_ACADEMICAS C WHERE C.ID_CURSO = :curso)", nativeQuery =
    // true)
    // public Optional<List<Horario>> eliminarHorariosPorCurso(@Param("curso") Long
    // curso);

    // @Modifying
    // @Transactional
    // @Query(value = "DELETE FROM HORARIOS H WHERE H.ID_CARGA_ACADEMICA IN (SELECT
    // C.ID FROM CARGAS_ACADEMICAS C WHERE C.ID_DOCENTE = :docente", nativeQuery =
    // true)
    // public Optional<List<Horario>> eliminarHorariosPorDocente(@Param("docente")
    // Long docente);
}
