package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;

@Entity
@Table(name = "barrrio")
public class Barrio {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR")
    private String nombre;
    @Column(name = "codigo_postal", columnDefinition = "INT")
    private Integer codigoPostal;

    @OneToOne
    //TODO oneToOne
    private Ciudad ciudad;
}
