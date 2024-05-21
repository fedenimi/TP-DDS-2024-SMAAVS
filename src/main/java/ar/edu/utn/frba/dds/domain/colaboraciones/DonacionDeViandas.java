package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class DonacionDeViandas implements Contribucion, Puntuable{
    private List<Vianda> viandasDonadas;
    private LocalDate fecha;
    private Colaborador colaborador;
    @Getter private float multiplicador;
    @Override
    public void contribuir() {

    }

    @Override
    public void aumentarPuntaje() {

    }
    @Override
    public float puntaje() {
        return 0;
    }

}
