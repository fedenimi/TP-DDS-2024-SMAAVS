package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Enviador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class InstanciadorColaborador {
    private Enviador enviador;
    public Colaborador crearColaborador(ColaboradorDO colaboradorDO, List<Colaborador> colaboradores) throws TelegramApiException {
        List<MedioDeContacto> mediosDeContacto = new ArrayList<>();
        mediosDeContacto.add(colaboradorDO.getMedioDeContacto());
        Optional<Colaborador> colaboradorOptional = this.colaboradorCon(colaboradorDO.getTipoDoc(), colaboradorDO.getDoc(), colaboradores);
        Colaborador colaborador;

        if (colaboradorOptional.isPresent()) {
            colaborador = colaboradorOptional.get();
        } else {
            colaborador = new Colaborador(TipoDeColaborador.HUMANA, mediosDeContacto, new Documento("12123123", TipoDocumento.DNI), colaboradorDO.getNombre(), colaboradorDO.getApellido());
            colaboradores.add(colaborador);
            enviador.enviar(colaboradorDO.getMedioDeContacto().getValor(), "Nuevo Registro",
                    "Hola " + colaboradorDO.getNombre() + ", muchas gracias por colaborar!\n" +
                            "Tu contraseña de ingreso es "+"password"+" y tu usuario es " + colaboradorDO.getNombre()
            );
            ServiceLocator.instanceOf(RepositorioColaboradores.class).guardar(colaborador);
        }
        return colaborador;
    }


    private Optional<Colaborador> colaboradorCon(String tipoDoc, String doc, List<Colaborador> colaboradores) {
        return colaboradores.stream()
                .filter(colaborador -> colaborador.getDocumento().getTipo().equals(tipoDoc) &&
                        colaborador.getDocumento().equals(doc))
                .findFirst();
    }

}
