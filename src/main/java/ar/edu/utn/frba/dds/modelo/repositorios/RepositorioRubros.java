package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioRubros implements IRepositorio<Rubro>, WithSimplePersistenceUnit {
    @Override
    public void guardar(Rubro rubro) {
        entityManager().persist(rubro);
    }

    @Override
    public void eliminar(Rubro rubro) {
        entityManager().remove(rubro);
    }

    @Override
    public Optional<Rubro> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Rubro.class, id));
    }

    @Override
    public List<Rubro> buscarTodos() {
        return entityManager()
                .createQuery("from " + Rubro.class.getName())
                .getResultList();
    }
}