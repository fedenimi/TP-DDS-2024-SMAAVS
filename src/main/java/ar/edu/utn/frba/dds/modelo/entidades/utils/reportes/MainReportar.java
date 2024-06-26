package com.itextpdf.hellopdf;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.reportes.Reporte;
import ar.edu.utn.frba.dds.modelo.entidades.utils.reportes.ReporteViandasColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.reportes.ReporteViandasHeladera;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDonacionesViandas;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainReportar {

    public static void main(String[] args) throws FileNotFoundException {
        List<MedioDeContacto> medios = new ArrayList<>();
        Colaborador martin = new Colaborador(TipoDeColaborador.HUMANA, medios, "DNI", "12345678", "Martin", "Martinez");
        Colaborador nico = new Colaborador(TipoDeColaborador.HUMANA, medios, "DNI", "12345678", "Nicolas", "Katz");
        Colaborador juan = new Colaborador(TipoDeColaborador.HUMANA, medios, "DNI", "12345678", "Juan", "Juan");

        Heladera hel1 = new Heladera(1);
        Heladera hel2 = new Heladera(2);
        Heladera hel3 = new Heladera(3);

        List<Reporte> creadoresDeReporte = new ArrayList<>();

        List<DistribucionDeViandas> distribucionesDeViandas = new ArrayList<>();
        distribucionesDeViandas.add(new DistribucionDeViandas(3, martin, hel1, hel2));
        distribucionesDeViandas.add(new DistribucionDeViandas(1, nico, hel1, hel2));
        distribucionesDeViandas.add(new DistribucionDeViandas(10, juan, hel1, hel2));
        distribucionesDeViandas.add(new DistribucionDeViandas(8, nico, hel1, hel2));
        RepositorioDistribucionesViandas repoDist = new RepositorioDistribucionesViandas(distribucionesDeViandas);

        List<DonacionDeViandas> donacionesDeViandas = new ArrayList<>();
        Vianda vianda1 = new Vianda(hel3);
        Vianda vianda2 = new Vianda(hel3);
        Vianda vianda3 = new Vianda(hel3);
        Vianda vianda4 = new Vianda(hel3);
        Vianda vianda5 = new Vianda(hel3);
        Vianda vianda6 = new Vianda(hel3);
        donacionesDeViandas.add(new DonacionDeViandas(martin, Arrays.asList(vianda1, vianda1, vianda1, vianda1, vianda1)));
        donacionesDeViandas.add(new DonacionDeViandas(nico, Arrays.asList(vianda2, vianda3)));
        donacionesDeViandas.add(new DonacionDeViandas(juan, Arrays.asList(vianda4, vianda5)));
        donacionesDeViandas.add(new DonacionDeViandas(nico, Arrays.asList(vianda6, vianda6)));
        RepositorioDonacionesViandas repoDona = new RepositorioDonacionesViandas(donacionesDeViandas);

        ReporteViandasColaborador reporteViandasColaborador = new ReporteViandasColaborador(repoDona, repoDist, "reporteViandasColaborador");
        creadoresDeReporte.add(reporteViandasColaborador);
        ReporteViandasHeladera reporteViandasHeladera = new ReporteViandasHeladera(repoDona, repoDist, "reporteViandasHeladera");
        creadoresDeReporte.add(reporteViandasHeladera);

        for (int i = 0; i < creadoresDeReporte.size(); i++) {
            Reporte reporte = creadoresDeReporte.get(i);
            String pdf = reporte.crearReporte();
            try (Document document = new Document(new PdfDocument(new PdfWriter("./" + reporte.getNombreArchivo() + ".pdf")))) {
                document.add(new Paragraph(pdf));
            }
        }
    }
}