package ar.edu.utn.frba.dds.modelo.cronJobs;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.PuntoDonacionCreate;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.utils.ServicioPuntos;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;

import java.io.IOException;
import java.util.List;

public class MainRecomendadorPuntos {
    public static void main(String[] args) throws IOException{
        RepositorioHeladeras repositorioHeladeras = ServiceLocator.instanceOf(RepositorioHeladeras.class);
        List<Heladera> heladeras = repositorioHeladeras.buscarTodos();
        List<Direccion> direccionesHeladeras = heladeras.stream().map(Heladera::getDireccion).toList();
        for(Direccion direccion : direccionesHeladeras){
            try {
                List<PuntoDonacionCreate> nuevoPunto = ServicioPuntos.instancia("http://127.0.0.1:8001/")
                        .agregarNuevoPunto(direccion.getNombre_direccion(), direccion.getPunto().getLatitud(), direccion.getPunto().getLongitud(), direccion.getDireccion());
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al agregar el punto: " + e.getMessage());
            }
        }
    }
}
