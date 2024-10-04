package ar.edu.utn.frba.dds.controladores;

import io.javalin.http.Context;
import org.apache.commons.collections.map.HashedMap;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ControladorReportes implements ICrudViewsHandler{
    @Override
    public void index(Context context) {
        File file = new File("src/main/resources/public/reportes/reporteFallasHeladera.pdf");
        long lastModified = file.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha = sdf.format(lastModified);
        Map<String, Object> model = new HashedMap();
        model.put("fecha", fecha);
        context.render("archivos/reportes.hbs", model);
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
