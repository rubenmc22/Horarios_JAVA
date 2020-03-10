package com.cpu.sistema_horario_java.app.horario;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
@Table(name = "horarios")
public class Horario {

    @Id
    @Builder.Default
    private Long id = null;

    @MapsId
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_carga_academica")
    private CargaAcademica cargaAcademica;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "horario_carga_academica")
    @JoinColumn(name = "id_periodo")
    private Map<Dia, Periodo> periodoDia;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

}