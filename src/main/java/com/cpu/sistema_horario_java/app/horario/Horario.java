package com.cpu.sistema_horario_java.app.horario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.periodo.Period;
import com.cpu.sistema_horario_java.app.util.types.Dia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "id_carga_academica")
    private CargaAcademica cargaAcademica;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia")
    private Dia dia;

    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private Period periodo;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

}