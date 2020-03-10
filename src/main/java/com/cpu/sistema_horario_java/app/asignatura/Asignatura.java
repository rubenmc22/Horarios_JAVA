package com.cpu.sistema_horario_java.app.asignatura;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = null;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    // @Builder.Default
    // @ToString.Exclude
    // @ManyToMany
    // @JoinTable(name = "asignatura_docente",
    // joinColumns = @JoinColumn(name = "id_asignatura", nullable = false),
    // inverseJoinColumns = @JoinColumn(name = "id_profesor", nullable = false))
    // private List<Docente> docentes = new ArrayList<>();

    @Builder.Default
    @Column(name = "estatus")
    private Boolean estatus = true;

    // public void addDocente(Docente docente) {
    // this.docentes.add(docente);
    // docente.getAsignaturas().add(this);
    // }

    // public void removeDocente(Docente docente) {
    // this.docentes.remove(docente);
    // docente.getAsignaturas().remove(this);
    // }

}