package com.cpu.sistema_horario_java.app.horario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cpu.sistema_horario_java.app.util.types.Dia;

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

    @Column(name = "profesor"/* , nullable = false */)
    private String profesor;

    @Column(name = "materia"/* , nullable = false */)
    private String materia;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia"/* , nullable = false */)
    private Dia dia;

    @Column(name = "hora"/* , nullable = false */)
    private String hora;

    @Column(name = "estatus")
    private Boolean estatus = true;

}