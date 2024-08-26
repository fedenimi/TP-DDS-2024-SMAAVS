package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;

@Entity
@Table(name = "ciudad")
public class Ciudad {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR")
    private String nombre;
    @OneToOne
    //TODO oneToOne
    private PartidoODepartamento partidoODepartamento;
}
