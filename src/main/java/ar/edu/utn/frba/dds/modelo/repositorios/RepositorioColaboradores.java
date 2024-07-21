package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioColaboradores;

import java.util.List;
import java.util.Optional;

public class RepositorioColaboradores implements IRepositorioColaboradores {
    private List<Colaborador> colaboradores;
    private static RepositorioColaboradores instance = null;
    public static RepositorioColaboradores getInstance() {
        if(instance == null)
            instance = new RepositorioColaboradores();
        return instance;
    }

    @Override
    public Optional<Colaborador> buscarPor(String doc, String tipoDoc) {
        return null;
    }
}
