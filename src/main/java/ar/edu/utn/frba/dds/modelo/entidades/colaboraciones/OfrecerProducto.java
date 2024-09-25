package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Oferta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
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
