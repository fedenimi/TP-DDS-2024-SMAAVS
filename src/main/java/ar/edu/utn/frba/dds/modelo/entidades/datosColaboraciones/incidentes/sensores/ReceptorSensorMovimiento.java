package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

import java.time.LocalDateTime;
import java.util.List;

public class ReceptorSensorMovimiento extends Receptor {
    private List<Fraude> fraudes;

    public void agregarFraude() {
        Fraude fraude = new Fraude(LocalDateTime.now());
        fraudes.add(fraude);
    }

}