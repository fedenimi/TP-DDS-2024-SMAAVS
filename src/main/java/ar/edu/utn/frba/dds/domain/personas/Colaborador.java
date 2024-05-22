package ar.edu.utn.frba.dds.domain.personas;

import ar.edu.utn.frba.dds.domain.colaboraciones.Contribucion;
import ar.edu.utn.frba.dds.domain.datosPersonas.*;
import ar.edu.utn.frba.dds.domain.localizacion.Punto;
import ar.edu.utn.frba.dds.domain.localizacion.RecomendadorDePuntos;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
@NoArgsConstructor
public class Colaborador {
    private List<Contribucion> contribuciones;
    private Formulario formularioRespondido;
    private TipoDeColaborador tipoDeColaborador;
    private List<MedioDeContacto> mediosDeContacto;
    private List<Respuesta> respuestas;
    private float puntos;
    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;

    public Colaborador(List<Contribucion> contribuciones, Formulario formularioRespondido, TipoDeColaborador tipoDeColaborador, List<MedioDeContacto> mediosDeContacto, List<Respuesta> respuestas, String tipoDocumento, String documento, String nombre, String apellido) {
        this.contribuciones = contribuciones;
        this.formularioRespondido = formularioRespondido;
        this.tipoDeColaborador = tipoDeColaborador;
        this.mediosDeContacto = mediosDeContacto;
        this.respuestas = respuestas;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntos = 0;
    }

    public void agregarContribucion(Contribucion unaContribucion) {
        this.contribuciones.add(unaContribucion);
    }
    public void ingresarRespuesta(Pregunta pregunta){

    }

    public List<Punto> puntosDeHeladeraRecomendados(Punto punto, Integer radioEnMetros) throws IOException {
        return RecomendadorDePuntos.getInstance().puntosDeHeladeraRecomendados(punto, radioEnMetros);
    }

    public void intercambiarPuntos(float puntos){
        this.puntos -= puntos;
    }

    public void sumarPuntos(float puntos){
        this.puntos += puntos;
    }

}
