package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.ReceptorSensorTemperatura;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioReceptoresTemperatura;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioReceptoresTemperatura implements IRepositorio<ReceptorSensorTemperatura>, WithSimplePersistenceUnit {

    private static RepositorioReceptoresTemperatura instance = null;
    List<ReceptorSensorTemperatura> receptores = new ArrayList<>();
    public static RepositorioReceptoresTemperatura getInstance() {
        if(instance == null)
            instance = new RepositorioReceptoresTemperatura();
        return instance;
    }
    @Override
    public Optional<ReceptorSensorTemperatura> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(ReceptorSensorTemperatura.class, id));
    }

    @Override
    public List<ReceptorSensorTemperatura> buscarTodos() {
        return entityManager()
                .createQuery("from " + ReceptorSensorTemperatura.class.getName())
                .getResultList();
    }

    @Override
    public void guardar(ReceptorSensorTemperatura sensorTemperatura) {
        entityManager().persist(sensorTemperatura);
    }

    @Override
    public void eliminar(ReceptorSensorTemperatura receptorSensorTemperatura) {
        entityManager().remove(receptorSensorTemperatura);
    }
}
