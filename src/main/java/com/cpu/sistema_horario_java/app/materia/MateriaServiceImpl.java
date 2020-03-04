package com.cpu.sistema_horario_java.app.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    MateriaRepository repo;

    @Autowired
    MateriaMapper matMapper;

    @Override
    public MateriaDTO buscar(Long id) {
        Optional<Materia> materia = repo.findById(id);

        if (materia.isPresent()) {
            return matMapper.toDTO(materia.get());
        }
        return null;
    }

    @Override
    public Set<MateriaDTO> listar() {
        return repo.findAll().stream().map(materia -> matMapper.toDTO(materia))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public MateriaDTO guardar(MateriaDTO dto) {
        Optional<MateriaDTO> materia = repo.findByNombre(dto.getNombre());

        if (!materia.isPresent()) {
            return matMapper.toDTO(matMapper.toModel(dto));
        }

        return null;
    }

    @Override
    public MateriaDTO actualizar(Long id, MateriaDTO materia) {

        return repo.findById(id).map(m -> {
            return matMapper.toDTO(repo.save(matMapper.toModel(materia, m)));
        }).orElseGet(() -> {
            materia.setId(id);
            return matMapper.toDTO(repo.save(matMapper.toModel(materia)));
        });
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}