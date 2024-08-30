package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.ReceptorSensorTemperatura;

import java.util.List;
import java.util.Optional;

public interface IRepositorioReceptoresTemperatura {
    public Optional<ReceptorSensorTemperatura> buscar(Long idHeladera);
    public List<ReceptorSensorTemperatura> buscarTodos();
    public void agregar(ReceptorSensorTemperatura receptorSensorTemperatura);

    public void eliminar(ReceptorSensorTemperatura receptorSensorTemperatura);
}
