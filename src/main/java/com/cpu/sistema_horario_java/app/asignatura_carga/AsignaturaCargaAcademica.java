package com.cpu.sistema_horario_java.app.asignatura_carga;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.carga.CargaAcademica;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asignatura_cargas_academicas")
@ToString(exclude = { "asignatura", "cargaAcademica" })
public class AsignaturaCargaAcademica {

    @EmbeddedId
    private AsignaturaCargaId id;

    @ManyToOne
    @MapsId("id_asignatura")
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @ManyToOne
    @MapsId("id_carga_academica")
    @JoinColumn(name = "id_carga_academica")
    private CargaAcademica cargaAcademica;

    private Integer horas;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

    public AsignaturaCargaAcademica(Asignatura asignatura, CargaAcademica cargaAcademica) {
        this.asignatura = asignatura;
        this.cargaAcademica = cargaAcademica;
        this.id = new AsignaturaCargaId(asignatura.getId(), cargaAcademica.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AsignaturaCargaAcademica that = (AsignaturaCargaAcademica) o;

        return Objects.equals(asignatura, that.asignatura) && Objects.equals(cargaAcademica, that.cargaAcademica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asignatura, cargaAcademica);
    }

}