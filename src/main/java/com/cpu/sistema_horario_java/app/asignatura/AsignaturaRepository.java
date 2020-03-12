package com.cpu.sistema_horario_java.app.asignatura;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    public Optional<AsignaturaDTO> findByNombre(String nombre);

    @Modifying
    @Transactional
    @Query("DELETE CargaAcademica c WHERE c.asignatura = :asignatura")
    public void deleteAllCargaAssociatedToAsignatura(@Param("asignatura") Asignatura asignatura);
}