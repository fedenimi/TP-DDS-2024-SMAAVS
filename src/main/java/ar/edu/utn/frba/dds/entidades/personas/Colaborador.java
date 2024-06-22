package ar.edu.utn.frba.dds.entidades.personas;

import ar.edu.utn.frba.dds.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.entidades.localizacion.RecomendadorDePuntos;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
@NoArgsConstructor
public class Colaborador {
    private List<Puntuable> puntuables;
    private List<OfrecerProducto> ofrecerProductos;
    private FormularioRespondido formularioRespondido;
    private TipoDeColaborador tipoDeColaborador;
    private List<MedioDeContacto> mediosDeContacto;
    private float puntosDisponibles;
    private float puntosCanjeados;
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
        this.puntosDisponibles = 0;
    }

    public void agregarPuntuable(Puntuable puntuable) {
       this.puntuables.add(puntuable);
    }

    public void agregarOfrecerProducto(OfrecerProducto ofrecerProducto) {
        this.ofrecerProductos.add(ofrecerProducto);
    }

    public List<Punto> puntosDeHeladeraRecomendados(Punto punto, Integer radioEnMetros) throws IOException {
        return RecomendadorDePuntos.getInstance().puntosDeHeladeraRecomendados(punto, radioEnMetros);
    }

    public Integer sumatoriaDeMesesDeHeladerasActivas() {
        return this.puntuables.stream().mapToInt(puntuable -> puntuable.cantidadDeMesesSiendoHeladera()).sum();
    }

    public void intercambiarPuntos(float puntos){
        this.puntosDisponibles -= puntos;
    }

    public void sumarPuntos(float puntos){
        this.puntosDisponibles += puntos;
    }

}
