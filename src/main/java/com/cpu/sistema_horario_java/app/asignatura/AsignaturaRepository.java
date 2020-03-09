package com.cpu.sistema_horario_java.app.asignatura;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    public Optional<AsignaturaDTO> findByNombre(String nombre);
}