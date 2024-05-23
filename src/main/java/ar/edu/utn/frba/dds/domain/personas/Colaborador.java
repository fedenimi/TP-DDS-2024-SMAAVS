package ar.edu.utn.frba.dds.domain.personas;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.domain.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.domain.datosPersonas.*;
import ar.edu.utn.frba.dds.domain.localizacion.Punto;
import ar.edu.utn.frba.dds.domain.localizacion.RecomendadorDePuntos;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
@NoArgsConstructor
public class Colaborador {
    private List<Puntuable> puntuables;
    private List<OfrecerProducto> ofrecerProductos;
    private Formulario formularioRespondido;
    private TipoDeColaborador tipoDeColaborador;
    private List<MedioDeContacto> mediosDeContacto;
    private List<Respuesta> respuestas;
    private float puntos;
    @Getter
    private String tipoDocumento;
    @Getter
    private String documento;
    private String nombre;
    private String apellido;

    public Colaborador(TipoDeColaborador tipoDeColaborador, List<MedioDeContacto> mediosDeContacto,  String tipoDocumento, String documento, String nombre, String apellido) {
        this.tipoDeColaborador = tipoDeColaborador;
        this.mediosDeContacto = mediosDeContacto;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntos = 0;
    }

    //public void agregarContribucion(Contribucion unaContribucion) {
    //   this.contribuciones.add(unaContribucion);
    //}
    public void ingresarRespuesta(Pregunta pregunta){

    }

    public List<Punto> puntosDeHeladeraRecomendados(Punto punto, Integer radioEnMetros) throws IOException {
        return RecomendadorDePuntos.getInstance().puntosDeHeladeraRecomendados(punto, radioEnMetros);
    }

    public Integer sumatoriaDeMesesDeHeladerasActivas() {
        return this.puntuables.stream().mapToInt(puntuable -> puntuable.cantidadDeMesesActiva()).sum();
    }

    public void intercambiarPuntos(float puntos){
        this.puntos -= puntos;
    }

    public void sumarPuntos(float puntos){
        this.puntos += puntos;
    }

}
