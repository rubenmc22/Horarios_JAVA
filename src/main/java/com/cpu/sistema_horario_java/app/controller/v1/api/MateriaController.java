package com.cpu.sistema_horario_java.app.controller.v1.api;

import com.cpu.sistema_horario_java.app.materia.MateriaService;
import com.cpu.sistema_horario_java.util.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/reservation")
@Api(value = "horario-application", description = "Operaciones pertinentes al manejo de las materias dentro de la aplicación.")
public class MateriaController {

    @Autowired
    private MateriaService matService;

    @GetMapping("/materias")
    public Response getAllMaterias(){
        return Response.ok().setPayload(matService.getAllMaterias());
    }
}