package com.cpu.sistema_horario_java.app.asignatura_carga;

import java.util.List;

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.carga.CargaAcademica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaCargaAcademicaRepository extends JpaRepository<AsignaturaCargaAcademica, Long> {

    public List<AsignaturaCargaAcademica> findByAsignatura(Asignatura asignatura);

    public List<AsignaturaCargaAcademica> findByCargaAcademica(CargaAcademica cargaAcademica);

}