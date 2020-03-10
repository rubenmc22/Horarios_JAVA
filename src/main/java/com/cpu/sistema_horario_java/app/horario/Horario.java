package com.cpu.sistema_horario_java.app.horario;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.periodo.Period;
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
    private Map<Dia, Period> periodoDia;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

}