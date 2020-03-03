package com.cpu.sistema_horario_java.app.profesor;

public class ProfesorMapper {

    public Profesor toModel(ProfesorDTO dto) {
        Profesor modelo = new Profesor();

        modelo.setId(dto.getId());
        modelo.setNombre(dto.getNombre());
        modelo.setApellido(dto.getApellido());
        modelo.setCedula(dto.getCedula());
        modelo.setTelefono(dto.getTelefono());
        modelo.setCorreo(dto.getCorreo());
        modelo.setEstatus(dto.getEstatus());

        return modelo;
    }

    public ProfesorDTO toDTO(Profesor modelo) {
        ProfesorDTO dto = new ProfesorDTO();

        dto.setId(modelo.getId());
        dto.setNombre(modelo.getNombre());
        dto.setApellido(modelo.getApellido());
        dto.setCedula(modelo.getCedula());
        dto.setTelefono(modelo.getTelefono());
        dto.setCorreo(modelo.getCorreo());
        dto.setEstatus(modelo.getEstatus());

        return dto;
    }
}