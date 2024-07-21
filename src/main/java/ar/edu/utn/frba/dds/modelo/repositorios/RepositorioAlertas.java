package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioAlertas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RepositorioAlertas implements IRepositorioAlertas {
    List<Alerta> alertas = new ArrayList<>();
    private static RepositorioAlertas instance = null;
    public static RepositorioAlertas getInstance() {
        if(instance == null)
            instance = new RepositorioAlertas();
        return instance;
    }
    @Override
    public void guardar(Alerta alerta) {
        alertas.add(alerta);
    }

    @Override
    public void eliminar(Alerta alerta) {
        alertas.remove(alerta);
    }

    @Override
    public Alerta buscar(String idHeladera) {
        return null;
    }

    @Override
    public List<Alerta> buscarTodos() {
        return alertas;
    }
}
