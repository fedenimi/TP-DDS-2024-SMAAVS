package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Llamador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioTecnicos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlertadorDeTecnicos {

    private static AlertadorDeTecnicos instance = null;
    public static AlertadorDeTecnicos getInstance() {
        if(instance == null)
            instance = new AlertadorDeTecnicos();
        return instance;
    }
    public void alertar(Heladera heladera, IBuscadorDeTecnicos buscadorDeTecnicos) {
        Tecnico tecnicoMasCercano = buscadorDeTecnicos.buscarTecnicoMasCercanoA(heladera);
        System.out.println("Se alerto al tecnico: " + tecnicoMasCercano.getNombre() + " " + tecnicoMasCercano.getApellido());
        tecnicoMasCercano.addFalloHeladera(
                FalloHeladeraTecnico.builder().fechaYHora(LocalDateTime.now()).visitaRealizada(false).heladera(heladera).build()
        );
        System.out.println("La fecha es: " + tecnicoMasCercano.getFalloHeladera().get(tecnicoMasCercano.getFalloHeladera().size() - 1).getFechaYHora());
        ServiceLocator.instanceOf(RepositorioTecnicos.class).modificar(tecnicoMasCercano);
        List<MedioDeContacto> mediosDeContacto = new ArrayList<>();
        mediosDeContacto.add(tecnicoMasCercano.getMedioDeContacto());
        Llamador.getInstance().llamar(mediosDeContacto, "Anda a arreglar la heladera " + heladera.getId(), "HELADERA ROTA");
    }
}
