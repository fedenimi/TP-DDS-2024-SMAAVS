package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.PersonaVulnerableDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaPersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPersonasVulnerables;

import java.util.ArrayList;
import java.util.List;

public class ServicePersonasVulnerables {
    public static PersonaVulnerableDTO toPersonaVulnerableDTO (PersonaVulnerable personaVulnerable) {
        return PersonaVulnerableDTO.builder().
                id(personaVulnerable.getId().toString()).
                nombre(personaVulnerable.getNombre()).
                domicilio(personaVulnerable.getDomicilio().getDireccion()).
                tipo_documento(personaVulnerable.getDocumento().getTipo().toString()).
                num_documento(personaVulnerable.getDocumento().getNumero()).
                fecha_de_nacimiento(personaVulnerable.getFechaDeNacimiento().toString()).
                build();
    }

    public static void asignarTarjetaA(PersonaVulnerable personaVulnerable, RepositorioPersonasVulnerables repositorioPersonasVulnerables) {
        List<PersonaVulnerable> personasVulnerables = repositorioPersonasVulnerables.buscarTodos();
        List<String> codigosAlfanumericos = personasVulnerables.stream().map(p -> p.getTarjeta().getCodigo()).toList();
        String codigo = ServiceTarjetas.codigoAlfaNumericoTarjetaPersonaVulnerable();
        if(codigosAlfanumericos.contains(codigo)) {
            asignarTarjetaA(personaVulnerable, repositorioPersonasVulnerables);
        } else {
            personaVulnerable.setTarjeta(TarjetaPersonaVulnerable.builder()
                    .codigo(codigo)
                    .usosMaximoBase(4)
                    .usosMaximoPorMenor(2)
                    .usos(new ArrayList<>())
                    .build());
        }
    }
}