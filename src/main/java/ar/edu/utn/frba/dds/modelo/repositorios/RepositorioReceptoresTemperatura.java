package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.ReceptorSensorTemperatura;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioReceptoresTemperatura;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioReceptoresTemperatura implements IRepositorioReceptoresTemperatura {

    private static RepositorioReceptoresTemperatura instance = null;
    List<ReceptorSensorTemperatura> receptores = new ArrayList<>();
    public static RepositorioReceptoresTemperatura getInstance() {
        if(instance == null)
            instance = new RepositorioReceptoresTemperatura();
        return instance;
    }
    @Override
    public Optional<ReceptorSensorTemperatura> buscar(Long idHeladera) {
        return receptores.stream().filter(receptor -> receptor.getHeladera().getId().equals(idHeladera)).findFirst();
    }

    @Override
    public List<ReceptorSensorTemperatura> buscarTodos() {
        return receptores;
    }

    @Override
    public void agregar(ReceptorSensorTemperatura sensorTemperatura) {
        receptores.add(sensorTemperatura);
    }
}
