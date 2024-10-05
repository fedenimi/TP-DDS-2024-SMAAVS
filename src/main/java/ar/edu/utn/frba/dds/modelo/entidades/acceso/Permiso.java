package ar.edu.utn.frba.dds.modelo.entidades.acceso;

import io.javalin.security.RouteRole;

public enum Permiso implements RouteRole {
    ADMIN,
    HUMANA,
    JURIDICA,
    DONAR_VIANDAS,
    DONAR_DINERO,
    DISTRIBUIR_VIANDAS,
    REGISTRAR_PERSONA_VULNERABLE,
    ADMINISTRAR_HELADERA
}
