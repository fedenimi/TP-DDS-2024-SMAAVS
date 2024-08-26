package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioFallasTecnicas;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class RepositorioFallasTecnicas implements IRepositorioFallasTecnicas {
    private static RepositorioFallasTecnicas instance = null;
    public static RepositorioFallasTecnicas getInstance() {
        if(instance == null) {
            instance = new RepositorioFallasTecnicas();
        }
        return instance;
    }
    List<FallaTecnica> fallasTecnicas;

    @Override
    public void guardar(FallaTecnica fallaTecnica) {

    }

    @Override
    public void eliminar(FallaTecnica fallaTecnica) {

    }

    @Override
    public FallaTecnica buscar(String idHeladera) {
        return null;
    }

    @Override
    public List<Alerta> buscarTodos() {
        return null;
    }
}
