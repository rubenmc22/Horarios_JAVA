package com.cpu.sistema_horario_java.app.carga;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.curso.Curso;
import com.cpu.sistema_horario_java.app.docente.Docente;

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

    // @MapsId
    @OneToOne
    @ToString.Exclude
    private Asignatura asignatura;

    // @MapsId
    @OneToOne
    @ToString.Exclude
    private Curso curso;

    // @MapsId
    @OneToOne
    @ToString.Exclude
    private Docente docente;

    private Integer horas;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CargaAcademica that = (CargaAcademica) o;

        return this.asignatura.getId() == that.asignatura.getId() && this.curso.getId() == that.curso.getId()
                && this.docente.getId() == that.docente.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.asignatura.getId(), this.curso.getId(), this.docente.getId());
    }

}