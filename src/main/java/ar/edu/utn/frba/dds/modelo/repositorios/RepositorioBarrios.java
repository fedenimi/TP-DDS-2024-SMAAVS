package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Barrio;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioBarrios implements IRepositorio<Barrio>, WithSimplePersistenceUnit {
    @Override
    public void guardar(Barrio barrio) {
        entityManager().persist(barrio);
    }

    @Override
    public void eliminar(Barrio barrio) {
        entityManager().remove(barrio);
    }

    @Override
    public Optional<Barrio> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Barrio.class, id));
    }

    @Override
    public List<Barrio> buscarTodos() {
        return entityManager()
                .createQuery("from " + Barrio.class.getName())
                .getResultList();
    }
}
