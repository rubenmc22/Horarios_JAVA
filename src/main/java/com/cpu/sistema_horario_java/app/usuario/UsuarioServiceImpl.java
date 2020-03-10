package com.cpu.sistema_horario_java.app.usuario;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.HORARIO;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import com.cpu.sistema_horario_java.app.util.exception.ExceptionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repo;

    @Override
    public Usuario buscar(final Long id) {
        final Optional<Usuario> model = repo.findById(id);

        if (model.isPresent()) {
            return model.get();
        }
        throw exception(HORARIO, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Override
    public Usuario guardar(final Usuario dto) {
        final Optional<Usuario> model = repo.findById(dto.getId());

        if (!model.isPresent()) {
            return repo.save(dto);
        }

        throw exception(HORARIO, DUPLICATE_ENTITY, dto.getId().toString());
    }

    @Override
    public Usuario actualizar(final Long id, final Usuario dto) {

        final Optional<Usuario> model = repo.findById(id);

        if (model.isPresent()) {
            dto.setId(id);
            repo.save(dto);
        }
        throw exception(HORARIO, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public void eliminar(final Long id) {
        final Optional<Usuario> model = repo.findById(id);

        if (model.isPresent()) {
            repo.delete(model.get());
        }
        throw exception(HORARIO, ENTITY_NOT_FOUND, id.toString());
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    static RuntimeException exception(final EntityType entityType, final ExceptionType exceptionType,
            final String... args) {
        return SystemException.throwException(entityType, exceptionType, args);
    }
}