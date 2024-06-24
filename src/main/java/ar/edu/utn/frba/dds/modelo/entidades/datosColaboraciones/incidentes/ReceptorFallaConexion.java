package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.Receptor;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

import java.time.LocalDateTime;
import java.util.List;

public class ReceptorFallaConexion extends Receptor {
    private List<FallaConexion> fallaConexion;

    public void recibirDato() {
        this.registrarAlerta(Estado.FALLA_CONEXION);
        this.agregarFallaConexion();
    }
    public void agregarFallaConexion() {
        FallaConexion fallaConexion = new FallaConexion(LocalDateTime.now());
    }
}
