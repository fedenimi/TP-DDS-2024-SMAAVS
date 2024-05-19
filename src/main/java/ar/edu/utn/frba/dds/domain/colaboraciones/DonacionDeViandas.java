package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;

import java.time.LocalDate;
import java.util.List;

public class DonacionDeViandas implements Contribucion{
    private List<Vianda> viandasDonadas;
    private LocalDate fecha;
    private Colaborador colaborador;
    private float multiplicador;
    @Override
    public void contribuir() {

    }
}
