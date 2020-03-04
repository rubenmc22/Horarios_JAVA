package com.cpu.sistema_horario_java.app.profesor;

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
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellido;

    @Column(name = "cedula", length = 50, nullable = false)
    private String cedula;

    @Column(name = "telefono", length = 100)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "estatus")
    private Boolean estatus = true;

    public Profesor(String nombre, String apellido, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }

}