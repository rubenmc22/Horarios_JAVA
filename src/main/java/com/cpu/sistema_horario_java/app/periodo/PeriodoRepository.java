package com.cpu.sistema_horario_java.app.periodo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {

    public Periodo findByBloqueHorario(Integer bloque);

    @Query("SELECT p FROM Periodo p WHERE p.estatus = true AND p.bloqueHorario < 14")
    public List<Periodo> periodosParaAsignar();

}