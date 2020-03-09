package com.cpu.sistema_horario_java.app.asignatura_carga;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaCargaId implements Serializable {

    private static final long serialVersionUID = 8051698651713021590L;

    @Column(name = "id_asignatura")
    private Long idAsignatura;

    @Column(name = "id_carga_academica")
    private Long idCarga;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AsignaturaCargaId that = (AsignaturaCargaId) o;

        return Objects.equals(idAsignatura, that.idAsignatura) && Objects.equals(idCarga, that.idCarga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsignatura, idCarga);
    }
}