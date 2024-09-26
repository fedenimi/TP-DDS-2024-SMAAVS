package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;

import java.util.List;
import java.util.Optional;

public interface IRepositorio <T> {

    public void guardar(T t);
    public void eliminar(T t);
    public Optional<T> buscar(Long id);
    public List<T> buscarTodos();
}
