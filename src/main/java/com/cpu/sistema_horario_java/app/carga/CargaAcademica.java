package com.cpu.sistema_horario_java.app.carga;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.asignatura_carga.AsignaturaCargaAcademica;
import com.cpu.sistema_horario_java.app.curso.Curso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cargas_academicas")
@Slf4j
public class CargaAcademica {

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = null;

    private String nombre;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "asignatura", cascade = CascadeType.ALL)
    List<AsignaturaCargaAcademica> asignaturas = new ArrayList<>();

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "cargaAcademica", cascade = CascadeType.ALL)
    private List<Curso> cursos = new ArrayList<>();

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

    public void addAsignatura(Asignatura asignatura) {
        AsignaturaCargaAcademica asignaturaCargaAcademica = new AsignaturaCargaAcademica(asignatura, this);
        log.info("Guardando: " + asignaturaCargaAcademica);
        asignaturas.add(asignaturaCargaAcademica);
        asignatura.getCargas().add(asignaturaCargaAcademica);
    }

    public void removeAsignatura(Asignatura asignatura) {
        for (Iterator<AsignaturaCargaAcademica> iterator = asignaturas.iterator(); iterator.hasNext();) {
            AsignaturaCargaAcademica asignaturaCargaAcademica = iterator.next();

            if (asignaturaCargaAcademica.getCargaAcademica().equals(this)
                    && asignaturaCargaAcademica.getAsignatura().equals(asignatura)) {
                iterator.remove();
                asignaturaCargaAcademica.getAsignatura().getCargas().remove(asignaturaCargaAcademica);
                asignaturaCargaAcademica.setAsignatura(null);
                asignaturaCargaAcademica.setCargaAcademica(null);
            }
        }
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setCargaAcademica(this);
    }

    public void removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setCargaAcademica(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CargaAcademica that = (CargaAcademica) o;

        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

}