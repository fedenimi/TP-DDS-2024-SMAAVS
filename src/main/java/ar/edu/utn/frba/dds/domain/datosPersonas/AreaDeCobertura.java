package ar.edu.utn.frba.dds.domain.datosPersonas;

import ar.edu.utn.frba.dds.domain.localizacion.Barrio;
import ar.edu.utn.frba.dds.domain.localizacion.PartidoODepartamento;
import ar.edu.utn.frba.dds.domain.localizacion.ProvinciaOEstado;

import java.util.List;

public class AreaDeCobertura {
    private Pais pais;
    private List<ProvinciaOEstado> provinciasOEstados;
    private List<PartidoODepartamento> partidosODepartamentos;
    private List<Ciudad> ciudades;
    private List<Barrio> barrios;
}
