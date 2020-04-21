package com.cpu.sistema_horario_java.app.horas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BloqueHorarioRepository extends JpaRepository<BloqueHorario, Long> {

    public BloqueHorario findByBloqueHorario(Integer bloque);

    @Query("SELECT b FROM BloqueHorario b WHERE b.estatus = true AND b.bloqueHorario < 14")
    public List<BloqueHorario> horasParaAsignar();

    @Query(value = "SELECT * FROM BLOQUES_HORARIOS B WHERE B.ESTATUS = TRUE AND B.ID NOT IN (SELECT H.ID_BLOQUE_HORARIO FROM HORARIOS H, CARGAS_ACADEMICAS C WHERE H.DIA = :dia AND H.ID_CARGA_ACADEMICA = C.ID AND C.ID_CURSO = :curso)", nativeQuery = true)
    public List<BloqueHorario> horasLibresPorCursoPorDia(@Param("dia") String dia, @Param("curso") Long curso);

    @Query(value = "SELECT * FROM BLOQUES_HORARIOS B WHERE B.ESTATUS = TRUE AND B.ID NOT IN (SELECT H.ID_BLOQUE_HORARIO FROM HORARIOS H JOIN CARGAS_ACADEMICAS CA ON H.ID_CARGA_ACADEMICA = CA.ID WHERE H.DIA = :dia AND CA.ID_DOCENTE = :docente)", nativeQuery = true)
    public List<BloqueHorario> horasLibresPorDocentePorDia(@Param("dia") String dia, @Param("docente") Long docente);

}