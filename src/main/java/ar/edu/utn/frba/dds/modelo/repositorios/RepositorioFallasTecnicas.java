package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RepositorioFallasTecnicas {
    List<FallaTecnica> fallasTecnicas;

}
