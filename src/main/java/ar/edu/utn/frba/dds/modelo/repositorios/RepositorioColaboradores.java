package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioColaboradores;

import java.util.List;
import java.util.Optional;

public class RepositorioColaboradores implements IRepositorioColaboradores {
    private List<Colaborador> colaboradores;

    @Override
    public Optional<Colaborador> buscarPor(String doc, String tipoDoc) {
        return null;
    }
}
