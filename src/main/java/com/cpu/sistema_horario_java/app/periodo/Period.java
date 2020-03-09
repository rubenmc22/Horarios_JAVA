package com.cpu.sistema_horario_java.app.periodo;

import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "periodos")
public class Period implements Comparable<Period> {

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = null;
    private Integer bloqueHorario;
    private LocalTime inicioPeriodo;
    private LocalTime finPeriodo;
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

        Period that = (Period) o;

        return this.id == that.id && this.bloqueHorario == that.bloqueHorario
                && this.inicioPeriodo.equals(that.inicioPeriodo) && this.finPeriodo.equals(that.finPeriodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.bloqueHorario, this.inicioPeriodo, this.finPeriodo);
    }

    @Override
    public int compareTo(Period o) {
        return Integer.compare(this.bloqueHorario, o.bloqueHorario);

    }
}