package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Frecuencia;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;

public class DonacionDeDinero implements Contribucion, Puntuable{
    private LocalDate fechaDeInicio;
    private Integer monto;
    private Frecuencia frecuencia;
    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    public DonacionDeDinero(Integer monto, Colaborador colaborador) {
        this.monto = monto;
        this.colaborador = colaborador;
    }

    @Override
    public void contribuir() {
    }
    @Override
    public float puntaje() {
        return monto;
    }

    @Override
    public int cantidadDeMesesActiva() {
        return 0;
    }
}
