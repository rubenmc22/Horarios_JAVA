package com.cpu.sistema_horario_java.app.docente;

import java.util.List;
import java.util.ArrayList;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;

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
@Table(name = "docentes")
public class Docente {

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = null;

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

    @Builder.Default
    @ToString.Exclude
    @ManyToMany(mappedBy = "docentes")
    List<Asignatura> asignaturas = new ArrayList<>();

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

}