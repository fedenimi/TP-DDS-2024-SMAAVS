package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioTecnicos;

import java.util.List;

public class BuscadorDeTecnicos implements IBuscadorDeTecnicos{
    private RepositorioTecnicos repositorioTecnicos;

    public BuscadorDeTecnicos(RepositorioTecnicos repositorioTecnicos){
        this.repositorioTecnicos = repositorioTecnicos;
    }
    public Tecnico buscarTecnicoMasCercanoA(Heladera heladera){
        List<Tecnico> tecnicos = repositorioTecnicos.buscarTodos();
        return tecnicos.stream().min((t1, t2) -> {
            double distancia1 = t1.getUltimoPunto().distanciaA(heladera.getDireccion().getPunto());
            double distancia2 = t2.getUltimoPunto().distanciaA(heladera.getDireccion().getPunto());
            return Double.compare(distancia1, distancia2);
        }).orElse(null);
    }
}
