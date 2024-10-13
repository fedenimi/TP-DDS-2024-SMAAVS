package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.PuntoDonacionCreate;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioPuntos {
    private static ServicioPuntos instancia = null;
    private String urlApi = "";
    private Retrofit retrofit;


    private ServicioPuntos(String urlApi) {
        this.urlApi = urlApi;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ServicioPuntos instancia(String urlApi){
        if(instancia== null){
            instancia = new ServicioPuntos(urlApi);
        }
        return instancia;
    }

    public List<Punto> listadoDePuntos(Double latitud, Double longitud, Integer limit) throws IOException {
        PuntosService puntosService = this.retrofit.create(PuntosService.class);
        Call<List<Punto>> requestPuntos = puntosService.puntos(latitud, longitud, limit);
        Response<List<Punto>> responsePuntos = requestPuntos.execute();
        return responsePuntos.body();
    }

    public List<PuntoDonacionCreate> agregarNuevoPunto(String nombre, Double latitud, Double longitud, String direccion) throws IOException {
        PuntosService puntosService = this.retrofit.create(PuntosService.class);
        PuntoDonacionCreate puntoDonacionCreate = new PuntoDonacionCreate(nombre, latitud, longitud, direccion);

        List<PuntoDonacionCreate> puntos = new ArrayList<>();
        puntos.add(puntoDonacionCreate);
        Call<List<PuntoDonacionCreate>> requestPunto = puntosService.agregarPunto(
                puntos
        );
        Response<List<PuntoDonacionCreate>> responsePunto = requestPunto.execute();

        if (!responsePunto.isSuccessful() || responsePunto.body() == null) {
            throw new IOException("Error al agregar el punto: " + responsePunto.errorBody().string());
        }
        return responsePunto.body();
    }

}
