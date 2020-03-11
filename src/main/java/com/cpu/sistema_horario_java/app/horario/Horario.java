package com.cpu.sistema_horario_java.app.horario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.periodo.Periodo;
import com.cpu.sistema_horario_java.app.util.types.Dia;

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
@Table(name = "horarios", uniqueConstraints = @UniqueConstraint(columnNames = { "id_carga_academica", "dia",
        "id_periodo" }))
public class Horario {

    @Id
    @Builder.Default
    private Long id = null;

    @MapsId
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_carga_academica")
    private CargaAcademica cargaAcademica;

    @Enumerated(EnumType.STRING)
    private Dia dia;

    @OneToOne
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_periodo")
    private Periodo periodo;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

}