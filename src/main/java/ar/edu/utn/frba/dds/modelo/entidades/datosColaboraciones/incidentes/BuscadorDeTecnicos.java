package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;

public class BuscadorDeTecnicos implements IBuscadorDeTecnicos{
    public Tecnico buscarTecnicoMasCercanoA(Heladera heladera){
        return new Tecnico(
                1L,
                "Juan",
                "Perez",
                new Documento("123", TipoDocumento.DNI),
                "201232",
                new MedioDeContacto("f@f.com", TipoDeContacto.MAIL),
                null);
    }
}
