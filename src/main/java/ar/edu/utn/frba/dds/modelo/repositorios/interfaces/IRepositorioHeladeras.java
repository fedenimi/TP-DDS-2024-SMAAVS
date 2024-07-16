package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

import java.util.Optional;

public interface IRepositorioHeladeras {
    public Optional<Heladera> buscar(String id);
}
