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

    
    public void Contribucion(Colaborador colaborador, List<Vianda> viandasDonadas){
        this.colaborador = colaborador;
        this.viandasDonadas = viandasDonadas;
    }

    @Override
    public void contribuir() {
        Calculador.getInstance().aumentarPuntaje(this);
    }

    @Override
    public void Contribucion(Colaborador colaborador, Integer cantidad) {

    }

    @Override
    public float puntaje() {
        return viandasDonadas.size();
    }



}
