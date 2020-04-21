package com.cpu.sistema_horario_java.app.usuario;

import com.cpu.sistema_horario_java.app.util.types.EntityType;
import com.cpu.sistema_horario_java.app.util.exception.SystemException;

import static com.cpu.sistema_horario_java.app.util.types.EntityType.USUARIO;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.ENTITY_NOT_FOUND;
import static com.cpu.sistema_horario_java.app.util.exception.ExceptionType.WRONG_PASSWORD;

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
        } else {
            throw exception(USUARIO, ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Override
    public Usuario guardar(final Usuario dto) {
        final Optional<Usuario> model = repo.findByCedula(dto.getCedula());

        if (!model.isPresent()) {
            return repo.save(dto);
        } else {
            throw exception(USUARIO, DUPLICATE_ENTITY, dto.getId().toString());
        }
    }

    @Override
    public Usuario actualizar(final Long id, final Usuario dto) {

        final Optional<Usuario> model = repo.findById(id);

        if (model.isPresent()) {
            dto.setId(id);
            return repo.save(dto);
        } else {
            throw exception(USUARIO, ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    public void eliminar(final Long id) {
        final Optional<Usuario> model = repo.findById(id);

        if (model.isPresent()) {
            repo.delete(model.get());
        } else {
            throw exception(USUARIO, ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    public Usuario login(Usuario dto) {

        final Optional<Usuario> model = repo.findByCedula(dto.getCedula());

        if (model.isPresent()) {
            if (model.get().getPassword().equals(dto.getPassword())) {
                return model.get();
            } else {
                throw exception(USUARIO, WRONG_PASSWORD, dto.getCedula());
            }
        } else {
            throw exception(USUARIO, ENTITY_NOT_FOUND, dto.getCedula());
        }
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