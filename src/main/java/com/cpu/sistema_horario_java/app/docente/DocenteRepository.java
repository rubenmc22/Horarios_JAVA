package com.cpu.sistema_horario_java.app.docente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    public Optional<DocenteDTO> findByNombre(String nombre);
}