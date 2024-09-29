package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "barrio")
public class Barrio {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;
    @Column(name = "codigo_postal", columnDefinition = "INT")
    private Integer codigoPostal;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;
}
