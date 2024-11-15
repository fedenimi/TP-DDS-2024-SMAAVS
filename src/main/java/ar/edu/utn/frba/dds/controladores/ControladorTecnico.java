package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioTecnicos;
import io.javalin.http.Context;

public class ControladorTecnico implements ICrudViewsHandler{
    RepositorioTecnicos repositorioTecnicos;
    public ControladorTecnico(RepositorioTecnicos repositorioTecnicos) {
        this.repositorioTecnicos = repositorioTecnicos;
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
    public void save(Context context) throws Exception {

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
