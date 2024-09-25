package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import io.javalin.http.Context;

public class ControladorDistribuirViandas implements ICrudViewsHandler{
    private RepositorioPuntuables repositorioPuntuables;

    public ControladorDistribuirViandas(RepositorioPuntuables repositorioPuntuables) {
        this.repositorioPuntuables = repositorioPuntuables;
    }
    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
