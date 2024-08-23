package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "medio_de_contacto")
public class MedioDeContacto {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "valor", columnDefinition = "varchar(255)")
    private String valor;

    @Column(name = "tipo", columnDefinition = "varchar(8)")
    @Enumerated(EnumType.STRING)
    private TipoDeContacto tipo;

    public MedioDeContacto(String valor, TipoDeContacto tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }
}
