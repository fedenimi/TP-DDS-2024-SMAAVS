package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioUsuarios implements IRepositorio<Usuario>, WithSimplePersistenceUnit {
    @Override
    public void guardar(Usuario usuario) {
        entityManager().persist(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        entityManager().remove(usuario);
    }

    @Override
    public Optional<Usuario> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Usuario.class, id));
    }

    @Override
    public List<Usuario> buscarTodos() {
        return entityManager()
                .createQuery("from " + Usuario.class.getName())
                .getResultList();
    }
}
