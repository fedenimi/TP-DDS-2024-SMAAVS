package ar.edu.utn.frba.dds.domain.personas;

import ar.edu.utn.frba.dds.domain.colaboraciones.Contribucion;
import ar.edu.utn.frba.dds.domain.datosPersonas.Formulario;
import ar.edu.utn.frba.dds.domain.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.domain.datosPersonas.Respuesta;
import ar.edu.utn.frba.dds.domain.datosPersonas.TipoDeColaborador;

import java.util.List;

public class Colaborador {
    private List<Contribucion> contribuciones;
    private Formulario formularioRespondido;
    private TipoDeColaborador tipoDeColaborador;
    private List<MedioDeContacto> mediosDeContacto;
    private List<Respuesta> respuestas;
    private float puntos;
}
