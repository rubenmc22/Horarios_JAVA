package com.cpu.sistema_horario_java.app.horarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;

import com.cpu.sistema_horario_java.app.cargas.CargaAcademica;
import com.cpu.sistema_horario_java.app.horas.BloqueHorario;
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
// TODO INCLUDE UNIQUE CONSTRAINT
@Table(name = "horarios"
// , uniqueConstraints = @UniqueConstraint(columnNames = { "id_carga_academica",
// "dia", "id_periodo" })
)
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HORARIOS")
    @SequenceGenerator(name = "SEQ_HORARIOS", initialValue = 1, allocationSize = 1)
    @Builder.Default
    private Long id = null;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_carga_academica")
    private CargaAcademica cargaAcademica;

    @Enumerated(EnumType.STRING)
    private Dia dia;

    @OneToOne
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "id_bloque_horario")
    private BloqueHorario bloqueHorario;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

}