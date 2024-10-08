package ar.edu.utn.frba.dds.modelo.entidades.utils.archivos;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.ColaboradorDO;
import ar.edu.utn.frba.dds.modelo.entidades.utils.InstanciadorColaboracion;
import ar.edu.utn.frba.dds.modelo.entidades.utils.InstanciadorColaborador;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {
    private InstanciadorColaborador instanciadorColaborador;
    private InstanciadorColaboracion instanciadorColaboracion = new InstanciadorColaboracion();

    public LectorCSV(InstanciadorColaborador instanciadorColaborador) {
        this.instanciadorColaborador = instanciadorColaborador;
    }
    public List<Puntuable> cargarContribuciones(List<Colaborador> colaboradores, File file) throws Exception {
        ArrayList<Puntuable> contribuciones = new ArrayList<Puntuable>();
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
                ColaboradorDO colaboradorDO = ColaboradorDO.of(tipoDoc, doc,nombre,apellido,medioDeContacto,fechaColaboracion,formaColaboracion,cantidad);
                Colaborador colaborador = instanciadorColaborador.crearColaborador(colaboradorDO, colaboradores);
                colaboradores.add(colaborador);
                instanciadorColaboracion.agregarColaboracion(contribuciones, formaColaboracion, colaborador, cantidad);
                lineaActual++;
            }
        } finally {
            if (lectorCSV != null) {
                lectorCSV.close();
            }
        }
        return contribuciones;
    }


}