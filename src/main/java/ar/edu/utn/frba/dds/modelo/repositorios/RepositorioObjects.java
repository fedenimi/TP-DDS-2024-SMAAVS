package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioObjects implements WithSimplePersistenceUnit {
    public void guardar(Object o) {
        entityManager().persist(o);
    }


    public void eliminar(Object o) {
        entityManager().remove(o);
    }


    public Optional<Object> buscar(Long id) {
        return Optional.empty();
    }

    public List<Object> buscarTodos() {
        return entityManager()
                .createQuery("from " + Alerta.class.getName())
                .getResultList();
    }
}
