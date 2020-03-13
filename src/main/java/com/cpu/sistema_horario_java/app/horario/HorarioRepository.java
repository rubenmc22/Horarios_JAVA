package com.cpu.sistema_horario_java.app.horario;

import java.util.Optional;

import com.cpu.sistema_horario_java.app.periodo.Periodo;
import com.cpu.sistema_horario_java.app.util.types.Dia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query(value = "SELECT h FROM Horario h WHERE h.dia = :dia and h.periodo = :periodo")
    public Optional<Horario> getHorarioAsignado(@Param("dia") Dia dia, @Param("periodo") Periodo periodo);
}
