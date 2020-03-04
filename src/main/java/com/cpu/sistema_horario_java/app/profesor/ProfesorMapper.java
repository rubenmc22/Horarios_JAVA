package com.cpu.sistema_horario_java.app.profesor;

import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public Profesor toModel(ProfesorDTO dto) {
        Profesor model = new Profesor();

        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setApellido(dto.getApellido());
        model.setCedula(dto.getCedula());
        model.setTelefono(dto.getTelefono());
        model.setCorreo(dto.getCorreo());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Profesor toModel(ProfesorDTO dto, Profesor model) {

        model.setNombre(dto.getNombre());
        model.setApellido(dto.getApellido());
        model.setCedula(dto.getCedula());
        model.setTelefono(dto.getTelefono());
        model.setCorreo(dto.getCorreo());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public ProfesorDTO toDTO(Profesor model) {
        ProfesorDTO dto = new ProfesorDTO();

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setApellido(model.getApellido());
        dto.setCedula(model.getCedula());
        dto.setTelefono(model.getTelefono());
        dto.setCorreo(model.getCorreo());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public ProfesorDTO toDTO(Profesor model, ProfesorDTO dto) {

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setApellido(model.getApellido());
        dto.setCedula(model.getCedula());
        dto.setTelefono(model.getTelefono());
        dto.setCorreo(model.getCorreo());
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}