package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioFallasTecnicas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@AllArgsConstructor
public class ReporteFallas implements Reporte {

    private RepositorioAlertas repositorioAlertas;
    private RepositorioFallasTecnicas repositorioFallasTecnicas;
    Map<Heladera, Integer> heladeraFallasMap;
    @Getter private String nombreArchivo;

    public ReporteFallas(RepositorioAlertas repositorioAlertas, RepositorioFallasTecnicas repositorioFallasTecnicas, String nombreArchivo) {
        this.repositorioAlertas = repositorioAlertas;
        this.repositorioFallasTecnicas = repositorioFallasTecnicas;
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public String crearReporte() {
        heladeraFallasMap = new HashMap<Heladera, Integer>();
        List <Alerta> alertas = repositorioAlertas.getAlertas();
        for(int i = 0; i < alertas.size(); i++) {
            Alerta alerta = alertas.get(i);
            if(CalculadorDeFechas.getInstance().esEstaSemana(LocalDate.from(alerta.getFechaYHora()))) {
                Heladera heladera = alerta.getHeladera();
                this.agregarFallaAHeladera(heladera);
            }
        }
        List <FallaTecnica> fallasTecnicas = repositorioFallasTecnicas.getFallasTecnicas();
        for(int i = 0; i < fallasTecnicas.size(); i++) {
            FallaTecnica fallaTecnica = fallasTecnicas.get(i);
            if(CalculadorDeFechas.getInstance().esEstaSemana(LocalDate.from(fallaTecnica.getFechaYHora()))) {
                Heladera heladera = fallaTecnica.getHeladera();
                this.agregarFallaAHeladera(heladera);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Fecha: ")
                .append(LocalDate.now().toString())
                .append("\n");
        for (Map.Entry<Heladera, Integer> entry : heladeraFallasMap.entrySet()) {
            result.append("Heladera: ")
            .append(entry.getKey().getId())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" viandas\n");
        }
        return result.toString();
    }
    public void agregarFallaAHeladera(Heladera heladera) {
        if(heladeraFallasMap.containsKey(heladera)){
            heladeraFallasMap.put(heladera, heladeraFallasMap.get(heladera) + 1);
        }
        else {
            heladeraFallasMap.put(heladera, 1);
        }

    }
}
