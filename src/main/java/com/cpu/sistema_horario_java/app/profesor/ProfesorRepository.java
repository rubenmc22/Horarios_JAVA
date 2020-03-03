package com.cpu.sistema_horario_java.app.profesor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    public ProfesorDTO findByNombre(String nombre);

    public ProfesorDTO findByApellido(String apellido);
}