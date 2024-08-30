package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;

import java.util.List;
import java.util.Optional;

public interface IRepositorioAlertas {
    public void guardar(Alerta alerta);
    public void eliminar(Alerta alerta);
    public Optional<Alerta> buscar(String idHeladera);
    public List<Alerta> buscarTodos();
}
