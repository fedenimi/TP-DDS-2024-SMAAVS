package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.Calculador;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class DonacionDeViandas implements Contribucion, Puntuable{
    private List<Vianda> viandasDonadas;
    private LocalDate fecha;
    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;
    @Override
    public void contribuir() {
        Calculador.getInstance().aumentarPuntaje(this);
    }


    @Override
    public float puntaje() {
        return viandasDonadas.size();
    }

}
