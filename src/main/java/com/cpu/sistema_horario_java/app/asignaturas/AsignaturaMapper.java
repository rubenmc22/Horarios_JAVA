package com.cpu.sistema_horario_java.app.asignaturas;

import org.springframework.stereotype.Component;

@Component
public class AsignaturaMapper {

        public Asignatura toModel(AsignaturaDTO dto) {
                Asignatura model = new Asignatura();

                model.setId(dto.getId());
                model.setNombre(dto.getNombre());
                model.setDescripcion(dto.getDescripcion());
                model.setEstatus(dto.getEstatus());

                return model;
        }

        public Asignatura toModel(AsignaturaDTO dto, Asignatura model) {

                model.setNombre(dto.getNombre());
                model.setDescripcion(dto.getDescripcion());
                model.setEstatus(dto.getEstatus());

                return model;
        }

        public AsignaturaDTO toDTO(Asignatura model) {
                AsignaturaDTO dto = new AsignaturaDTO();

                dto.setId(model.getId());
                dto.setNombre(model.getNombre());
                dto.setDescripcion(model.getDescripcion());
                dto.setEstatus(model.getEstatus());

                return dto;
        }

        public AsignaturaDTO toDTO(Asignatura model, AsignaturaDTO dto) {

                dto.setId(model.getId());
                dto.setNombre(model.getNombre());
                dto.setDescripcion(model.getDescripcion());
                dto.setEstatus(model.getEstatus());

                return dto;
        }
}