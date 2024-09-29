package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "area_de_cobertura")
public class AreaDeCobertura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "barrio_area",
            joinColumns = @JoinColumn(name = "area_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "barrio_id", referencedColumnName = "id")
    )
    private List<Barrio> barrios;
}
