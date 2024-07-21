package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioHeladeras;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioHeladeras implements IRepositorioHeladeras {
    private List<Heladera> heladeras = new ArrayList<>();
    private static RepositorioHeladeras instance = null;
    public static RepositorioHeladeras getInstance() {
        if(instance == null)
            instance = new RepositorioHeladeras();
        return instance;
    }

    @Override
    public Optional<Heladera> buscar(String id) {
        return null;
    }

    @Override
    public void agregar(Heladera heladera) {
        heladeras.add(heladera);
    }

}
