package ar.edu.utn.frba.dds.modelo.entidades.personas;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FalloHeladeraTecnico;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.AreaDeCobertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tecnico")
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", columnDefinition = "varchar(255)")
    private String nombre;

    @Column(name = "apellido", columnDefinition = "varchar(255)")
    private String apellido;

    @Embedded
    private Documento documento;

    @Column(name = "cuil", columnDefinition = "varchar(255)")
    private String cuil;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "medio_de_contacto_id")
    private MedioDeContacto medioDeContacto;

    @ManyToOne
    @JoinColumn(name = "area_de_cobertura_id")
    private AreaDeCobertura areaDeCobertura;

    @Embedded
    private Punto ultimoPunto;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "tecnico_id")
    private List<FalloHeladeraTecnico> falloHeladera;


    public void addFalloHeladera(FalloHeladeraTecnico falloHeladeraTecnico) {
        this.falloHeladera.add(falloHeladeraTecnico);
    }
}
