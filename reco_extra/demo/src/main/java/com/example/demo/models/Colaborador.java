package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "colaborador_id", nullable = true)
    private List<DonacionDeViandas> donaciones;

    @Column(name = "puntos_disponibles", columnDefinition = "FLOAT(10,2)")
    private Float puntosDisponibles;

    public boolean hizoDonacionesEnLosUltimosDias(int i, Integer finalMinDonaciones) {
        return this.donacionesEnLosUltimosDias(i).size() >= finalMinDonaciones;
    }

    private Collection<DonacionDeViandas> donacionesEnLosUltimosDias(int i) {
        return this.donaciones.stream().filter(donacion -> donacion.getFecha().isAfter(java.time.LocalDate.now().minusDays(i))).toList();
    }

    public boolean tienePuntos(Integer finalMinPuntos) {

        return this.puntosDisponibles >= finalMinPuntos;
    }
}
