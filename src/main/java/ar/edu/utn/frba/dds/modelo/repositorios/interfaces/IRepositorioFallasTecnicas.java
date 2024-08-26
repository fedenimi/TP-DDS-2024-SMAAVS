package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;

import java.util.List;

public interface IRepositorioFallasTecnicas {
    public void guardar(FallaTecnica fallaTecnica);
    public void eliminar(FallaTecnica fallaTecnica);

    public FallaTecnica buscar(String idHeladera);
    public List<Alerta> buscarTodos();
}
