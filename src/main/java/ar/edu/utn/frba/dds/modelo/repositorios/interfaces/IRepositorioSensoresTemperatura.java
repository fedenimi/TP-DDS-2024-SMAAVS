package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.SensorTemperatura;

import java.util.List;
import java.util.Optional;

public interface IRepositorioSensoresTemperatura {
    public Optional<SensorTemperatura> buscar(String idHeladera);
    public List<SensorTemperatura> buscarTodos();

    public void agregar(SensorTemperatura sensor);
}
