package com.cpu.sistema_horario_java.app.controller.v1.api;

import com.cpu.sistema_horario_java.app.materia.MateriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reservation")
public class MateriaController {

    @Autowired
    private MateriaService matService;

}