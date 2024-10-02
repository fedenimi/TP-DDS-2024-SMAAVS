package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.server.Router;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ControladorCargaMasivaColaboraciones implements ICrudViewsHandler{
    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        context.render("archivos/cargarCSV.hbs");
    }

    @Override
    public void save(Context context) {
        UploadedFile file = context.uploadedFile("file");
        File file1 = new File("src/main/resources/" + file.filename());
        try {
            file1.createNewFile();
            OutputStream os = context.outputStream();
            os.write(file.content().readAllBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
