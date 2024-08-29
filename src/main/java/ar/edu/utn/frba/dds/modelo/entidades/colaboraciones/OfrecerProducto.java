package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Oferta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ofrecer_producto")
public class OfrecerProducto {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

}
