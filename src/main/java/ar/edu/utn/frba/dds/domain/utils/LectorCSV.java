package ar.edu.utn.frba.dds.domain.utils;

import ar.edu.utn.frba.dds.domain.colaboraciones.Contribucion;
import ar.edu.utn.frba.dds.domain.datosPersonas.Formulario;
import ar.edu.utn.frba.dds.domain.datosPersonas.Pregunta;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
                String mail = linea[4];
                String fechaColaboracion = linea[5];
                String formaColaboracion = linea[6];
                String cantidad = linea[7];

                if(!this.existeColaboradorCon(tipoDoc, doc, colaboradores)) {
                    Colaborador colaborador = new Colaborador()
                }

                lineaActual++;
            }
        } finally {
            //Close the reader
            if (lectorCSV != null) {
                lectorCSV.close();
            }
        }

    }

}

