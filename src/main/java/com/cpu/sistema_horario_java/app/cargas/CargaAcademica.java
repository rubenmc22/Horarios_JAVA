package com.cpu.sistema_horario_java.app.cargas;

import static com.cpu.sistema_horario_java.app.util.types.Estatus.PENDIENTE;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cpu.sistema_horario_java.app.asignaturas.Asignatura;
import com.cpu.sistema_horario_java.app.cursos.Curso;
import com.cpu.sistema_horario_java.app.docentes.Docente;
import com.cpu.sistema_horario_java.app.util.types.Estatus;

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
@Table(name = "cargas_academicas", uniqueConstraints = @UniqueConstraint(columnNames = { "id_asignatura", "id_curso",
        "id_docente" }))
public class CargaAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARGAS")
    @SequenceGenerator(name = "SEQ_CARGAS", initialValue = 1, allocationSize = 1)
    @Builder.Default
    private Long id = null;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "id_docente")
    private Docente docente;

    @Builder.Default
    private Integer horas = 1;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Estatus estatus = PENDIENTE;

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