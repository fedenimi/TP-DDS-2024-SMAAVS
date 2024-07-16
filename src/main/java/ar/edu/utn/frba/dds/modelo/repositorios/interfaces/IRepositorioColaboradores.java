package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import java.util.Optional;

public interface IRepositorioColaboradores {
    public Optional<Colaborador> buscarPor(String doc, String tipoDoc);
}
