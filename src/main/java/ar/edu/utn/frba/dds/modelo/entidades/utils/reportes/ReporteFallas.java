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
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

@AllArgsConstructor
public class ReporteFallas implements Reporte {

    private List<Alerta> alertas;
    private List<FallaTecnica> fallasTecnicas;
    @Getter private Map<Heladera, Integer> heladeraFallasMap;
    @Getter private String nombreArchivo;
    private ReporteStringFallas reporteStringFallas;

    public ReporteFallas(List<Alerta> alertas, List<FallaTecnica> fallasTecnicas, String nombreArchivo, ReporteStringFallas reporteStringFallas) {
        this.alertas = alertas;
        this.fallasTecnicas = fallasTecnicas;
        this.nombreArchivo = nombreArchivo;
        this.reporteStringFallas = reporteStringFallas;
    }

    @Override
    public String crearReporte() {
        heladeraFallasMap = new HashMap<Heladera, Integer>();
        for(int i = 0; i < alertas.size(); i++) {
            Alerta alerta = alertas.get(i);
            if(CalculadorDeFechas.getInstance().esEstaSemana(LocalDateTime.from(alerta.getFechaYHora()))) {
                Heladera heladera = alerta.getHeladera();
                this.agregarFallaAHeladera(heladera);
            }
        }
        
        for(int i = 0; i < fallasTecnicas.size(); i++) {
            FallaTecnica fallaTecnica = fallasTecnicas.get(i);
            if(CalculadorDeFechas.getInstance().esEstaSemana(LocalDateTime.from(fallaTecnica.getFechaYHora()))) {
                Heladera heladera = fallaTecnica.getHeladera();
                this.agregarFallaAHeladera(heladera);
            }
        }

       return reporteStringFallas.crearReporteString(this);
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
