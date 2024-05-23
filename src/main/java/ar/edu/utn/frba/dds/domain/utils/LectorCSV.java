package ar.edu.utn.frba.dds.domain.utils;

import ar.edu.utn.frba.dds.domain.colaboraciones.*;
import ar.edu.utn.frba.dds.domain.datosPersonas.*;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectorCSV {
    public List<Contribucion> cargarContribuciones(List<Colaborador> colaboradores, File file) throws Exception {

        ArrayList<Contribucion> contribuciones = new ArrayList<Contribucion>();
        String [] linea;
        CSVReader lectorCSV = null;

        try {
            lectorCSV = new CSVReaderBuilder(new FileReader(file))
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator(';')
                            .build())
                    .build();

            int lineaActual = 0;

            while ((linea = lectorCSV.readNext()) != null) {

                String tipoDoc = linea[0];
                String doc = linea[1];
                String nombre = linea[2];
                String apellido = linea[3];
                MedioDeContacto medioDeContacto = new MedioDeContacto(linea[4], TipoDeContacto.MAIL);
                String fechaColaboracion = linea[5];
                String formaColaboracion = linea[6];
                int cantidad = Integer.parseInt(linea[7]);

                List<MedioDeContacto> mediosDeContacto = new ArrayList<>();
                mediosDeContacto.add(medioDeContacto);

                Optional<Colaborador> colaboradorOptional = this.colaboradorCon(tipoDoc, doc, colaboradores);
                Colaborador colaborador;

                if (colaboradorOptional.isPresent()) {
                    colaborador = colaboradorOptional.get();
                } else {
                    colaborador = new Colaborador(TipoDeColaborador.HUMANA, mediosDeContacto, tipoDoc, doc, nombre, apellido);
                    colaboradores.add(colaborador);
                }


                switch(formaColaboracion) {
                    case "DINERO":
                        contribuciones.add(new DonacionDeDinero(cantidad, colaborador));
                        break;
                    case "DONACION_VIANDAS":
                        contribuciones.add(new DonacionDeViandas(colaborador));
                        break;
                    case "REDISTRIBUCION_VIANDAS":
                        contribuciones.add(new DistribucionDeViandas(cantidad, colaborador));
                        break;
                    case "ENTREGA_TARJETAS":
                        for (int i = 0; i < cantidad; i++) {
                            contribuciones.add(new RegistroDePersonasVulnerables(colaborador));
                        }
                        break;
                }


                lineaActual++;

            }
        } finally {
            //Close the reader
            if (lectorCSV != null) {
                lectorCSV.close();
            }
        }
        return contribuciones;
    }

    private Optional<Colaborador> colaboradorCon(String tipoDoc, String doc, List<Colaborador> colaboradores) {
        return colaboradores.stream()
                            .filter(colaborador ->
                                colaborador.getTipoDocumento() == tipoDoc &&
                                colaborador.getDocumento() == doc).
                            findFirst();
    }

}

