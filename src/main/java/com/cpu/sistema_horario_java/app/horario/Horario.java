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

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.curso.Curso;
import com.cpu.sistema_horario_java.app.docente.Docente;
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
    @JoinColumn(name = "id_docente")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia")
    private Dia dia;

    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private Period periodo;

    // @Enumerated(EnumType.STRING)
    // @Column(name = "tipo_bloque_horario")
    // private TipoBloqueHorario tipoBloqueHorario;

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

}