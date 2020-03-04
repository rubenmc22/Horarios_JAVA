package com.cpu.sistema_horario_java.app.profesor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    public Optional<ProfesorDTO> findByNombre(String nombre);
}