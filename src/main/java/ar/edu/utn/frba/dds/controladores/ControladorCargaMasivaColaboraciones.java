package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.EnviadorDeMail;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.InstanciadorColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.archivos.LectorCSV;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioColaboradores;
import ar.edu.utn.frba.dds.server.Router;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import javax.validation.constraints.Null;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorCargaMasivaColaboraciones implements ICrudViewsHandler {
    RepositorioColaboradores repositorioColaboradores;

    public ControladorCargaMasivaColaboraciones(RepositorioColaboradores repositorioColaboradores) {
        this.repositorioColaboradores = repositorioColaboradores;
    }
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
    public void save(Context context) throws Exception {
        UploadedFile uploadedFile = context.uploadedFile("csv");
        File file = new File("src/main/resources/" + uploadedFile.filename());
        try {
            saveUploadedFile(uploadedFile.content(), file);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
        List<Colaborador> colaboradoresHumanos = ServiceLocator.instanceOf(RepositorioColaboradores.class).buscarTodos().stream().
                filter(colaborador -> colaborador.getTipoDeColaborador().equals(TipoDeColaborador.HUMANA)).
                toList();
        List<Colaborador> colaboradores = new ArrayList<>(colaboradoresHumanos);
        LectorCSV lectorCSV = new LectorCSV(new InstanciadorColaborador(new EnviadorDeMail()));
        lectorCSV.cargarContribuciones(colaboradores, file);
        file.delete();
    }

    private void saveUploadedFile(InputStream uploadedFileStream, File fileToSave) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = uploadedFileStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
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
