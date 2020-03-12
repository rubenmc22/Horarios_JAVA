package com.cpu.sistema_horario_java.app.docente;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    public Optional<Docente> findByCedula(String cedula);

    @Modifying
    @Transactional
    @Query("DELETE CargaAcademica c WHERE c.docente = :docente")
    public void deleteAllCargaAssociatedToDocente(@Param("docente") Docente docente);
}