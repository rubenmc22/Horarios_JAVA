package com.cpu.sistema_horario_java.app.profesor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellido;

    @Column(name = "cedula", length = 12, nullable = false)
    private String cedula;

    @Column(name = "telefono", length = 12)
    private String telefono;

    @Column(name = "correo", length = 50)
    private String correo;

    @Column(name = "estatus")
    private Boolean estatus = true;

}