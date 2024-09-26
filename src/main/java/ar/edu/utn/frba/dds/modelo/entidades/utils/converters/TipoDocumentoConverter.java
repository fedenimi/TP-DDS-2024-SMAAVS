package ar.edu.utn.frba.dds.modelo.entidades.utils.converters;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;

import javax.persistence.AttributeConverter;

public class TipoDocumentoConverter implements AttributeConverter<TipoDocumento, String> {

    private static TipoDocumentoConverter instance = null;
    public static TipoDocumentoConverter getInstance() {
        if(instance == null)
            instance = new TipoDocumentoConverter();
        return instance;
    }
    @Override
    public String convertToDatabaseColumn(TipoDocumento tipoDocumento) {
        String tipo = null;
                switch(tipoDocumento) {
                    case DNI: tipo = "DNI";
                    case LIBRETA: tipo = "LIBRETA";
                    case PASAPORTE: tipo = "PASAPORTE";
                    default:
        }
        return tipo;
    }

    @Override
    public TipoDocumento convertToEntityAttribute(String s) {
        TipoDocumento tipo = null;
        switch(s) {
            case "DNI": tipo = TipoDocumento.DNI;
            case "LIBRETA": tipo = TipoDocumento.LIBRETA;
            case "PASAPORTE": tipo = TipoDocumento.PASAPORTE;
            default:
        }
        return tipo;
    }
}
