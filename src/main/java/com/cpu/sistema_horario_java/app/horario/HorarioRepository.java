package com.cpu.sistema_horario_java.app.horario;

import java.util.Optional;

import com.cpu.sistema_horario_java.app.util.types.Dia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query("SELECT h FROM Horario h WHERE h.cargaAcademica.id = :cargaAcademica AND h.dia = :dia AND h.periodo.id = :periodo")
    public Optional<Horario> getHorarioByDetails(@Param("cargaAcademica") Long cargaAcademica,
            @Param("dia") Dia dia, @Param("periodo") Long periodo);
}
