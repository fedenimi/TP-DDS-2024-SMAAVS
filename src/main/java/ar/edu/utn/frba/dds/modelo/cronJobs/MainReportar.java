package ar.edu.utn.frba.dds.modelo.cronJobs;

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
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDonacionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioFallasTecnicas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainReportar {
    public static void main(String[] args) {
        List<Reporte> creadoresDeReporte = new ArrayList<>();
        List<MedioDeContacto> medios = new ArrayList<>();
        Colaborador martin = new Colaborador(TipoDeColaborador.HUMANA, medios, new Documento(1L, "123", TipoDocumento.DNI), "Martin", "Martinez");
        Colaborador nico = new Colaborador(TipoDeColaborador.HUMANA, medios, new Documento(2L, "124", TipoDocumento.DNI), "Nicolas", "Katz");
        Colaborador juan = new Colaborador(TipoDeColaborador.HUMANA, medios, new Documento(3L, "125", TipoDocumento.DNI), "Juan", "Juan");
        Heladera hel1 = new Heladera(1L, LocalDateTime.now());
        Heladera hel2 = new Heladera(2L, LocalDateTime.now());
        Heladera hel3 = new Heladera(3L, LocalDateTime.now());

        List<DistribucionDeViandas> distribucionesDeViandas = new ArrayList<>();
        distribucionesDeViandas.add(new DistribucionDeViandas(3, martin, hel1, hel2, LocalDate.now()));
        distribucionesDeViandas.add(new DistribucionDeViandas(1, nico, hel1, hel2, LocalDate.ofYearDay(2013, 2)));
        distribucionesDeViandas.add(new DistribucionDeViandas(10, juan, hel1, hel2, LocalDate.now()));
        distribucionesDeViandas.add(new DistribucionDeViandas(8, nico, hel1, hel2, LocalDate.now()));
        RepositorioDistribucionesViandas repoDist = new RepositorioDistribucionesViandas(distribucionesDeViandas);

        List<DonacionDeViandas> donacionesDeViandas = new ArrayList<>();
        Vianda vianda1 = new Vianda(hel3);
        Vianda vianda2 = new Vianda(hel3);
        Vianda vianda3 = new Vianda(hel3);
        Vianda vianda4 = new Vianda(hel3);
        Vianda vianda5 = new Vianda(hel3);
        Vianda vianda6 = new Vianda(hel3);
        donacionesDeViandas.add(new DonacionDeViandas(martin, Arrays.asList(vianda1, vianda1, vianda1, vianda1, vianda1), LocalDate.now()));
        donacionesDeViandas.add(new DonacionDeViandas(nico, Arrays.asList(vianda2, vianda3), LocalDate.now()));
        donacionesDeViandas.add(new DonacionDeViandas(juan, Arrays.asList(vianda4, vianda5), LocalDate.now()));
        donacionesDeViandas.add(new DonacionDeViandas(nico, Arrays.asList(vianda6, vianda6), LocalDate.now()));
        RepositorioDonacionesViandas repoDona = new RepositorioDonacionesViandas(donacionesDeViandas);

        Alerta alerta1 = new Alerta(1L,Estado.FRAUDE , LocalDateTime.now(), hel1);
        Alerta alerta2 = new Alerta(2L,Estado.FRAUDE, LocalDateTime.now(), hel2);
        Alerta alerta3 = new Alerta(3L,Estado.FRAUDE, LocalDateTime.now(), hel3);
        FallaTecnica fallaTecnica1 = new FallaTecnica(1L,nico, "Descripcion", "Foto", LocalDateTime.now(), hel1);
        FallaTecnica fallaTecnica2 = new FallaTecnica(2L,nico, "Descripcion", "Foto", LocalDateTime.now(), hel3);
        List<FallaTecnica> fallasTecnicas = new ArrayList<FallaTecnica>();
        fallasTecnicas.add(fallaTecnica1);
        fallasTecnicas.add(fallaTecnica2);
        List<Alerta> alertas = new ArrayList<Alerta>();
        alertas.add(alerta1);
        alertas.add(alerta2);
        alertas.add(alerta3);
        RepositorioFallasTecnicas repositorioFallasTecnicas = new RepositorioFallasTecnicas();
        RepositorioAlertas repositorioAlertas = new RepositorioAlertas();

        ReporteViandasColaborador reporteViandasColaborador = new ReporteViandasColaborador(repoDona, repoDist, "reporteViandasColaborador", new ReporteStringViandasColaborador());
        creadoresDeReporte.add(reporteViandasColaborador);
        ReporteViandasHeladera reporteViandasHeladera = new ReporteViandasHeladera(repoDona, repoDist, "reporteViandasHeladera", new ReporteStringViandasHeladera());
        creadoresDeReporte.add(reporteViandasHeladera);
        ReporteFallas reporteFallas = new ReporteFallas(repositorioAlertas, repositorioFallasTecnicas, "reporteFallasHeladera", new ReporteStringFallas());
        creadoresDeReporte.add(reporteFallas);
        for (int i = 0; i < creadoresDeReporte.size(); i++) {
            Reporte reporte = creadoresDeReporte.get(i);
            String pdf = reporte.crearReporte();
            CreadorDeReporte creadorDeReporte = new CreadorDeReporte();
            creadorDeReporte.crearReporte(pdf, reporte.getNombreArchivo(), FormatoReporte.PDF);
        }
    }
}
