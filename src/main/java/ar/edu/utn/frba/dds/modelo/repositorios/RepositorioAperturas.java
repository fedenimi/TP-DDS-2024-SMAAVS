package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Apertura;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioAperturas implements IRepositorio<Apertura>, WithSimplePersistenceUnit {
    @Override
    public void guardar(Apertura apertura) {
        beginTransaction();
        entityManager().persist(apertura);
        commitTransaction();
    }

    @Override
    public void eliminar(Apertura apertura) {
        beginTransaction();
        entityManager().remove(apertura);
        commitTransaction();
    }

    @Override
    public Optional<Apertura> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Apertura.class, id));
    }

    @Override
    public List<Apertura> buscarTodos() {
        return entityManager()
                .createQuery("from " + Apertura.class.getName())
                .getResultList();
    }
}
