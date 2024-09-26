package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "documento")
@Embeddable
public class Documento {

    @Column(name = "numero", columnDefinition = "varchar(20)")
    private String numero;

    @Column(name = "tipo_documento", columnDefinition = "varchar(10)")
    @Enumerated(EnumType.STRING)
    @Getter private TipoDocumento tipo;
}
