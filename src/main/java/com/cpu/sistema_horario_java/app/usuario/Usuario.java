package com.cpu.sistema_horario_java.app.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_USUARIOS")
    @SequenceGenerator(name = "SEQ_USUARIOS", initialValue = 1, allocationSize = 1)
    @Builder.Default
    private Long id = null;
    private String cedula;
    private String password;
    @Builder.Default
    private Boolean estatus = true;

}