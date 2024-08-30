package ar.edu.utn.frba.dds.modelo.repositorios.interfaces;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;

import java.util.List;
import java.util.Optional;

public interface IRepositorioFallasTecnicas {
    public void guardar(FallaTecnica fallaTecnica);
    public void eliminar(FallaTecnica fallaTecnica);

    public Optional<FallaTecnica> buscar(String idHeladera);
    public List<FallaTecnica> buscarTodos();
}
