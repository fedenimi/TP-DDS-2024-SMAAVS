package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Barrio;

import javax.persistence.*;

@Entity
@Table(name = "barrio_por_area")
public class BarrioPorArea {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;
}
