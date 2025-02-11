package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Enviador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioUsuarios;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
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
            colaborador = Colaborador.
                    builder().
                    tipoDeColaborador(TipoDeColaborador.HUMANA).
                    mediosDeContacto(mediosDeContacto).
                    documento(new Documento(colaboradorDO.getDoc(), TipoDocumento.DNI)).
                    formasDeColaborar(Arrays.asList(FormaColaboracion.DONACION_VIANDAS, FormaColaboracion.DONACION_DINERO, FormaColaboracion.REGISTRO_PERSONAS_VULNERABLES, FormaColaboracion.DISTRIBUCION_VIANDAS)).
                    nombre(colaboradorDO.getNombre()).
                    apellido(colaboradorDO.getApellido()).
                    mediosDeContacto(Arrays.asList(colaboradorDO.getMedioDeContacto())).
                    puntuables(new ArrayList<>()).
                    alertaSuscripciones(new ArrayList<>()).
                    suscripciones(new ArrayList<>()).
                    puntosDisponibles(0D).
                    puntosCanjeados(0D).
                    build();
            ServiceColaboradores.asignarTarjetaA(colaborador, ServiceLocator.instanceOf(RepositorioColaboradores.class));
            Usuario usuario = Usuario.
                    builder().
                    nombre(colaboradorDO.getNombre()).
                    contrasenia("password").
                    colaboradorAsociado(colaborador).
                    permisos(Arrays.asList(Permiso.HUMANA, Permiso.DISTRIBUIR_VIANDAS, Permiso.DONAR_VIANDAS, Permiso.DONAR_DINERO, Permiso.REGISTRAR_PERSONA_VULNERABLE)).
                    build();
            ServiceLocator.instanceOf(RepositorioUsuarios.class).guardar(usuario);
            System.out.println("Colaborador creado: " + colaborador.getNombre());
            enviador.enviar(colaboradorDO.getMedioDeContacto().getValor(), "Nuevo Registro",
                    "Hola " + colaboradorDO.getNombre() + ", muchas gracias por colaborar!\n" +
                            "Tu contraseña de ingreso es "+"password"+" y tu usuario es " + colaboradorDO.getNombre()
            );
            RepositorioColaboradores repositorioColaboradores = ServiceLocator.instanceOf(RepositorioColaboradores.class);

            repositorioColaboradores.guardar(colaborador);

            }
        return colaborador;
    }


    private Optional<Colaborador> colaboradorCon(String tipoDoc, String doc, List<Colaborador> colaboradores) {
        return colaboradores.stream()
                .filter(colaborador -> colaborador.getDocumento().getNumero().equals(doc) &&
                        colaborador.getDocumento().getTipo().toString().equals(tipoDoc))
                .findFirst();
    }

}
