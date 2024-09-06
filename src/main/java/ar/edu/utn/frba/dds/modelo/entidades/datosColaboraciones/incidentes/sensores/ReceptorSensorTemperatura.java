package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.CreadorAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receptor_sensor_temperatura")
public class ReceptorSensorTemperatura {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "receptor_sensor_temperatura_id")
    private List<Medicion> mediciones;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "heladera_id")
    private Heladera heladera;
    public void evaluarTemperatura(Temperatura temperatura) {
        this.agregarMedicion(temperatura);
        if (temperatura.getValor() < heladera.obtenerTemperaturaMinima() || temperatura.getValor() > this.getHeladera().obtenerTemperaturaMaxima()){
            Alerta alerta = this.crearAlerta(heladera, Estado.FALLA_TEMPERATURA);
            RepositorioAlertas.getInstance().guardar(alerta);
        }
    }
    public void agregarMedicion(Temperatura temperatura) {
        Medicion medicion = new Medicion(1L, temperatura.getValor(), LocalDateTime.now());
        mediciones.add(medicion);
    }

    public Alerta crearAlerta(Heladera heladera, Estado estado) {
        return CreadorAlerta.getInstance().crearAlerta(heladera, estado);
    }

    public LocalDateTime getUltimaConexion() {
        Optional<Medicion> medicionMasNueva = mediciones.stream().min(Comparator.comparing(Medicion::getFechaYHora));
        if(medicionMasNueva.isPresent()) return medicionMasNueva.get().getFechaYHora();
        return null;
    }
}
