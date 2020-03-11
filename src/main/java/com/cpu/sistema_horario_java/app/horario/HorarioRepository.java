package com.cpu.sistema_horario_java.app.horario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query(value = "SELECT * FROM horarios h WHERE h.id_carga_academica = :cargaAcademica and h.dia = :dia and h.id_periodo = :periodo", nativeQuery = true)
    public Optional<Horario> getHorarioByDetails(@Param("cargaAcademica") Long cargaAcademica, @Param("dia") String dia,
            @Param("periodo") Long periodo);
}
