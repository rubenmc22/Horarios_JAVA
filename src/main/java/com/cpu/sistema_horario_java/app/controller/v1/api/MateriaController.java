package com.cpu.sistema_horario_java.app.controller.v1.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/reservation")
@Api(value = "horario-application", description = "Operaciones pertinentes al manejo de las materias dentro de la aplicación.")
public class MateriaController {
    
}