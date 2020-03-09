package com.cpu.sistema_horario_java.app.horario;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import com.cpu.sistema_horario_java.app.util.types.BloqueHorario;
import com.cpu.sistema_horario_java.app.util.types.Dia;
import com.cpu.sistema_horario_java.app.util.types.TipoBloqueHorario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue
    private Long id;

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

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "bloque_horario")
    private Set<BloqueHorario> bloqueHorario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_bloque_horario")
    private TipoBloqueHorario tipoBloqueHorario;

    @Column(name = "estatus")
    private Boolean estatus = true;

}