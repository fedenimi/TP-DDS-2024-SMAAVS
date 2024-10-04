package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class RepositorioAlertaSuscripciones implements IRepositorio<AlertaSuscripcion>, WithSimplePersistenceUnit{
    List<AlertaSuscripcion> alertaSuscripcion = new ArrayList<>();
    @Override
    public void guardar(AlertaSuscripcion alertaSuscripcion) {
        entityManager().persist(alertaSuscripcion);
    }

    @Override
    public void eliminar(AlertaSuscripcion alertaSuscripcion) {
        entityManager().remove(alertaSuscripcion);
    }

    @Override
    public Optional<AlertaSuscripcion> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(AlertaSuscripcion.class, id));
    }

    @Override
    public List<AlertaSuscripcion> buscarTodos() {
        return entityManager()
                .createQuery("from " + AlertaSuscripcion.class.getName())
                .getResultList();
    }
}
