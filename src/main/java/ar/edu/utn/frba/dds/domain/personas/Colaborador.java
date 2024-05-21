package ar.edu.utn.frba.dds.domain.personas;

import ar.edu.utn.frba.dds.domain.colaboraciones.Contribucion;
import ar.edu.utn.frba.dds.domain.datosPersonas.*;
import ar.edu.utn.frba.dds.domain.localizacion.Punto;

import java.util.List;

public class Colaborador {
    private List<Contribucion> contribuciones;
    private Formulario formularioRespondido;
    private TipoDeColaborador tipoDeColaborador;
    private List<MedioDeContacto> mediosDeContacto;
    private List<Respuesta> respuestas;
    private float puntos;

    public void agregarContribucion(Contribucion unaContribucion) {
        this.contribuciones.add(unaContribucion);
    }
    public void ingresarRespuesta(Pregunta pregunta){

    }

    List<Punto> puntosDeHeladeraRecomendados(Punto punto, Integer  radioEnMetros){
        return null;
    }

    public void intercambiarPuntos(float puntos){
        this.puntos -= puntos;
    }

    public void sumarPuntos(float puntos){
        this.puntos += puntos;
    }

}
