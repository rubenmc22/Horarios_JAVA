package com.cpu.sistema_horario_java.app.horas;

import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bloques_horarios")
public class BloqueHorario implements Comparable<BloqueHorario> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BLOQUES_HORARIOS")
    @SequenceGenerator(name = "SEQ_BLOQUES_HORARIOS", initialValue = 1, allocationSize = 1)
    @Builder.Default
    private Long id = null;
    private Integer bloqueHorario;
    private LocalTime inicioBloque;
    private LocalTime finBloque;
    @Builder.Default
    private Boolean estatus = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BloqueHorario that = (BloqueHorario) o;

        return this.id == that.id && this.bloqueHorario == that.bloqueHorario
                && this.inicioBloque.equals(that.inicioBloque) && this.finBloque.equals(that.finBloque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.bloqueHorario, this.inicioBloque, this.finBloque);
    }

    @Override
    public int compareTo(BloqueHorario o) {
        return Integer.compare(this.bloqueHorario, o.bloqueHorario);

    }
}