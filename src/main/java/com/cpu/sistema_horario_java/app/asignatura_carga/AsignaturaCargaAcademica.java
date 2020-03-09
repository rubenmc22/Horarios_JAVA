package com.cpu.sistema_horario_java.app.asignatura_carga;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "id_carga_academica")
    private CargaAcademica cargaAcademica;

    private Integer horas;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

    public void setAsignaturaCargaAcademia(Asignatura asignatura, CargaAcademica cargaAcademica) {
        this.asignatura = asignatura;
        this.cargaAcademica = cargaAcademica;

        asignatura.getCargas().add(this);
        cargaAcademica.getAsignaturas().add(this);
    }

}