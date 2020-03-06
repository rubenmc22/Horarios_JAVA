package com.cpu.sistema_horario_java.app.horario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
}