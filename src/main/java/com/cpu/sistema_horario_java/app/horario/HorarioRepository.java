package com.cpu.sistema_horario_java.app.horario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query(value = "SELECT * FROM HORARIOS H, CARGAS_ACADEMICAS C WHERE H.DIA = :dia AND H.ID_PERIODO = :periodo AND H.ID_CARGA_ACADEMICA = C.ID AND C.ID_CURSO = :curso", nativeQuery = true)
    public Optional<List<Horario>> getHorarioAsignado(@Param("dia") String dia, @Param("periodo") Long periodo,
            @Param("curso") Long curso);

    @Query(value = "SELECT * FROM HORARIOS H, CARGAS_ACADEMICAS C WHERE H.ID_CARGA_ACADEMICA = C.ID AND C.ID_CURSO = :curso", nativeQuery = true)
    public Optional<List<Horario>> horariosPorCurso(@Param("curso") Long curso);
}
