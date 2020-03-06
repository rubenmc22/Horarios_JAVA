package com.cpu.sistema_horario_java.app.carga;

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
@Table(name = "cargas_academicas")
public class Carga {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 100/* , nullable = false */)
    private String descripcion;

    @Column(name = "profesor"/* , nullable = false */)
    private String profesor;

    @Column(name = "materia"/* , nullable = false */)
    private String materia;
    
    @Column(name = "curso"/* , nullable = false */)
    private String curso;;
    
    @Column(name = "horas"/* , nullable = false */)
    private Integer horas;

    @Column(name = "estatus")
    private Boolean estatus = true;

    public Carga(String nombre) {
        this.nombre = nombre;
    }

}