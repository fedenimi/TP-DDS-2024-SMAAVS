package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioSensoresTemperatura;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioSensoresTemperatura implements IRepositorioSensoresTemperatura {

    private static RepositorioSensoresTemperatura instance = null;
    List<SensorTemperatura> sensoresTemperatura = new ArrayList<>();
    public static RepositorioSensoresTemperatura getInstance() {
        if(instance == null)
            instance = new RepositorioSensoresTemperatura();
        return instance;
    }
    @Override
    public Optional<SensorTemperatura> buscar(String idHeladera) {
        return sensoresTemperatura.stream().filter(sensorTemperatura -> sensorTemperatura.getHeladera().getId().equals(idHeladera)).findFirst();
    }

    @Override
    public List<SensorTemperatura> buscarTodos() {
        return sensoresTemperatura;
    }

    @Override
    public void agregar(SensorTemperatura sensorTemperatura) {
        sensoresTemperatura.add(sensorTemperatura);
    }
}
