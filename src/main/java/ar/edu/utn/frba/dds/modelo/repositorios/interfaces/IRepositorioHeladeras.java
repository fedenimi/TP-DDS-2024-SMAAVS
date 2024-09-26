package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

import java.util.List;
import java.util.Optional;

public interface IRepositorioHeladeras {
    public Optional<Heladera> buscar(Long id);
    public void guardar(Heladera heladera);

    public void eliminar(Heladera heladera);

    public List<Heladera> buscarTodos();
}
