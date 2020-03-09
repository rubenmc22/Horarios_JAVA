package com.cpu.sistema_horario_java.app.carga;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.asignatura_carga.AsignaturaCargaAcademica;
import com.cpu.sistema_horario_java.app.curso.Curso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cargas_academicas")
public class CargaAcademica {

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = null;

    private String nombre;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "asignatura")
    List<AsignaturaCargaAcademica> asignaturas = new ArrayList<>();

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "cargaAcademica", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Curso> cursos = new ArrayList<>();

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

    public void addAsignatura(AsignaturaCargaAcademica asignaturaCargaAcademica) {
        this.asignaturas.add(asignaturaCargaAcademica);
    }

    public void removeAsignatura(AsignaturaCargaAcademica asignaturaCargaAcademica) {
        this.asignaturas.remove(asignaturaCargaAcademica);
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setCargaAcademica(this);
    }

    public void removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setCargaAcademica(null);
    }

}