package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.utils.archivos.CreadorPDF;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreadorDeReporte {

    public void crearReporte(String stringReporte, String nombreArchivo ,FormatoReporte formatoReporte) {
        switch(formatoReporte) {
            case PDF:
                CreadorPDF creadorPDF = new CreadorPDF();
                creadorPDF.crearPDF(stringReporte, nombreArchivo);
                break;
            case EXCEL:
                break;
            case CSV:
                break;
            case DOCX:
                break;

        }
    }
}
