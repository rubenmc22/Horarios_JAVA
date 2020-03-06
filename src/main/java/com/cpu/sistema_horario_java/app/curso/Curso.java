package com.cpu.sistema_horario_java.app.curso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 100/*, nullable = false*/)
    private String descripcion;

    @Column(name = "carga_academica"/*, nullable = false*/)
    private String cargaAcademica;

    @Column(name = "estatus")
    private Boolean estatus = true;

    public Curso(String nombre) {
        this.nombre = nombre;
    }

}