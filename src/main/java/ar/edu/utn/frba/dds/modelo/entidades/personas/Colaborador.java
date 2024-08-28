package ar.edu.utn.frba.dds.modelo.entidades.personas;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.FormularioRespondido;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.RecomendadorDePuntos;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
@NoArgsConstructor
@Getter
@Entity
@Table (name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinColumn(name = "colaborador_id")
    private List<Puntuable> puntuables;

    @OneToMany
    @JoinColumn(name = "colaborador_id")
    private List<OfrecerProducto> ofrecerProductos;

    @Transient //TODO
    private FormularioRespondido formularioRespondido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_colaborador")
    private TipoDeColaborador tipoDeColaborador;

    @OneToMany
    @JoinColumn(name = "colaborador_id")
    private List<MedioDeContacto> mediosDeContacto;

    @Column(name = "puntos_disponibles", columnDefinition = "FLOAT(10,2)")
    private Float puntosDisponibles;

    @Column(name = "puntos_canjeados", columnDefinition = "FLOAT(10,2)")
    private Float puntosCanjeados;

    @Transient //TODO
    private Documento documento;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @Column(name = "apellido", columnDefinition = "VARCHAR(255)")
    private String apellido;

    @Transient //TODO
    private TarjetaColaborador tarjeta;

    @ElementCollection
    @CollectionTable(name = "colaborador_forma_de_colaborar", joinColumns= @JoinColumn(name= "colaborador_id"))
    @Column(name = "forma_de_colaborar")
    private List<FormaColaboracion> formasDeColaborar;

    public Colaborador(TipoDeColaborador tipoDeColaborador, List<MedioDeContacto> mediosDeContacto,  Documento documento, String nombre, String apellido) {
        this.tipoDeColaborador = tipoDeColaborador;
        this.mediosDeContacto = mediosDeContacto;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntosDisponibles = 0F;
    }

    public void agregarPuntuable(Puntuable puntuable) {
       this.puntuables.add(puntuable);
       puntuable.setColaborador(this);
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
