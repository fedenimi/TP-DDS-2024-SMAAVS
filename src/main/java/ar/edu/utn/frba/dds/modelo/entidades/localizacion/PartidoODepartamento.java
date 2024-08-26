package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;

@Entity
@Table(name = "partido_o_departamento")
public class PartidoODepartamento {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR")
    private String nombre;
    @OneToOne
    //TODO oneToOne
    private ProvinciaOEstado provincia;
}
