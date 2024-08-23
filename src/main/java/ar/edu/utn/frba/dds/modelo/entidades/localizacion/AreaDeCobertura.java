package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "area_de_cobertura")
public class AreaDeCobertura {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinColumn(name = "area_cobertura_id")
    private List<BarrioPorArea> barriosPorArea;
}
