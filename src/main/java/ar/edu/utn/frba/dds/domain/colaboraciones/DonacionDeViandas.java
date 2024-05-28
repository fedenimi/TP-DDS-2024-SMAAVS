package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class DonacionDeViandas implements Puntuable{
    private List<Vianda> viandasDonadas;
    private LocalDate fecha;
    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    public DonacionDeViandas(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Override
    public float puntaje() {
        return viandasDonadas.size();
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }


}
