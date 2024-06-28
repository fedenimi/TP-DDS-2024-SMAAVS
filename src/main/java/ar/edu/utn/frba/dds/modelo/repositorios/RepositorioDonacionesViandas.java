package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
public class RepositorioDonacionesViandas {
    @Getter List<DonacionDeViandas> donacionDeViandas;
}
