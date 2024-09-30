package ar.edu.utn.frba.dds.modelo.entidades.personas;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Apertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.FormularioRespondido;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.RecomendadorDePuntos;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table (name = "colaborador")
@Builder
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "colaborador")
    private List<Puntuable> puntuables;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "colaborador")
    private List<OfrecerProducto> ofrecerProductos;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "formulario_respondido_id", referencedColumnName = "id")
    private FormularioRespondido formularioRespondido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_colaborador")
    private TipoDeColaborador tipoDeColaborador;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "colaborador_id")
    private List<MedioDeContacto> mediosDeContacto;

    @Column(name = "puntos_disponibles")
    private Double puntosDisponibles;

    @Column(name = "puntos_canjeados")
    private Double puntosCanjeados;

    @Getter
    @Embedded
    private Documento documento;

    @Getter
    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @Column(name = "apellido", columnDefinition = "VARCHAR(255)")
    private String apellido;

    @ElementCollection
    @CollectionTable(name = "colaborador_forma_de_colaborar", joinColumns= @JoinColumn(name= "colaborador_id"))
    @Column(name = "forma_de_colaborar")
    private List<FormaColaboracion> formasDeColaborar;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "colaborador_id")
    private List<AlertaSuscripcion> alertaSuscripciones;

    public Colaborador(TipoDeColaborador tipoDeColaborador, List<MedioDeContacto> mediosDeContacto,  Documento documento, String nombre, String apellido) {
        this.tipoDeColaborador = tipoDeColaborador;
        this.mediosDeContacto = mediosDeContacto;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntosDisponibles = 0D;
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

    public void guardarAlertaSuscripcion(AlertaSuscripcion alertaSuscripcion) {
        this.getAlertaSuscripciones().add(alertaSuscripcion);
    }
    public void guardarOfrecerProducto(OfrecerProducto ofrecerProducto) {
        this.getOfrecerProductos().add(ofrecerProducto);
    }
    public void modificarPuntosPorCanje(Double puntaje) {
        this.puntosDisponibles -= puntaje;
        this.puntosCanjeados += puntaje;
    }
}
