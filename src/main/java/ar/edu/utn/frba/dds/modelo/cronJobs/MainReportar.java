package ar.edu.utn.frba.dds.modelo.cronJobs;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.reportes.*;
import ar.edu.utn.frba.dds.modelo.repositorios.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainReportar {
    public static void main(String[] args) {
        List<Reporte> creadoresDeReporte = new ArrayList<>();
        List<DistribucionDeViandas> distribucionesDeViandas = ServiceLocator.instanceOf(RepositorioPuntuables.class).buscarTodos().stream().
                filter(d -> d instanceof DistribucionDeViandas).map(p -> (DistribucionDeViandas) p).collect(Collectors.toList());
        List<DonacionDeViandas> donacionesDeViandas = ServiceLocator.instanceOf(RepositorioPuntuables.class).buscarTodos().
                stream().filter(p -> p instanceof DonacionDeViandas).map(p -> (DonacionDeViandas) p).collect(Collectors.toList());
        List<FallaTecnica> fallasTecnicas = ServiceLocator.instanceOf(RepositorioFallasTecnicas.class).buscarTodos();
        List<Alerta> alertas = ServiceLocator.instanceOf(RepositorioAlertas.class).buscarTodos();
        ReporteViandasColaborador reporteViandasColaborador = new ReporteViandasColaborador(donacionesDeViandas, distribucionesDeViandas, "reporteViandasColaborador", new ReporteStringViandasColaborador());
        creadoresDeReporte.add(reporteViandasColaborador);
        ReporteViandasHeladera reporteViandasHeladera = new ReporteViandasHeladera(donacionesDeViandas, distribucionesDeViandas, "reporteViandasHeladera", new ReporteStringViandasHeladera());
        creadoresDeReporte.add(reporteViandasHeladera);
        ReporteFallas reporteFallas = new ReporteFallas(alertas, fallasTecnicas, "reporteFallasHeladera", new ReporteStringFallas());
        creadoresDeReporte.add(reporteFallas);
        for (int i = 0; i < creadoresDeReporte.size(); i++) {
            Reporte reporte = creadoresDeReporte.get(i);
            String pdf = reporte.crearReporte();
            CreadorDeReporte creadorDeReporte = new CreadorDeReporte();
            creadorDeReporte.crearReporte(pdf, reporte.getNombreArchivo(), FormatoReporte.PDF);
        }
    }
}
