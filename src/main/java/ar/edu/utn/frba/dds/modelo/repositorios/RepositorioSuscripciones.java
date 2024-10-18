package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.SuscripcionHumana;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioSuscripciones implements IRepositorio<SuscripcionHumana>, WithSimplePersistenceUnit {
    @Override
    public void guardar(SuscripcionHumana suscripcionHumana) {
        beginTransaction();
        entityManager().persist(suscripcionHumana);
        commitTransaction();
    }

    @Override
    public void eliminar(SuscripcionHumana suscripcionHumana) {
        beginTransaction();
        entityManager().remove(suscripcionHumana);
        commitTransaction();
    }

    @Override
    public Optional<SuscripcionHumana> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(SuscripcionHumana.class, id));
    }

    @Override
    public List<SuscripcionHumana> buscarTodos() {
        return entityManager()
                .createQuery("from " + SuscripcionHumana.class.getName())
                .getResultList();
    }
}
