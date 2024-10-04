package ar.edu.utn.frba.dds.modelo.entidades.utils;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

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

    public ListadoDePuntos listadoDePuntos() throws IOException {
        /*PuntosService puntosService = this.retrofit.create(PuntosService.class);
        Call<ListadoDePuntos> requestPuntos = puntosService.puntos();
        Response<ListadoDePuntos> responsePuntos = requestPuntos.execute();
        return responsePuntos.body();*/
        
    }
}
