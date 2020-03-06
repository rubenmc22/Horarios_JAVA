package com.cpu.sistema_horario_java.app.carga;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CargaRepository extends JpaRepository<Carga, Long> {

    public Optional<CargaDTO> findByNombre(String nombre);
}