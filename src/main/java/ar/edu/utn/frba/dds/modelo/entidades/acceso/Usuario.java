package ar.edu.utn.frba.dds.modelo.entidades.acceso;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import java.util.List;

public class Usuario {
    private String nombre;
    private String contrasenia;
    private List<Permiso> permisos;
    private Colaborador colaboradorAsociado;
}
