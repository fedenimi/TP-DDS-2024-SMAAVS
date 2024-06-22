package ar.edu.utn.frba.dds.entidades.utils;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioPuntos {
    private static ServicioPuntos instancia = null;
    private static final String urlApi = "https://d73307e9-4177-4d2d-babc-082da5291cda.mock.pstmn.io/api/";
    private Retrofit retrofit;

    private ServicioPuntos() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ServicioPuntos instancia(){
        if(instancia== null){
            instancia = new ServicioPuntos();
        }
        return instancia;
    }

    public ListadoDePuntos listadoDePuntos() throws IOException {
        PuntosService puntosService = this.retrofit.create(PuntosService.class);
        Call<ListadoDePuntos> requestPuntos = puntosService.puntos();
        Response<ListadoDePuntos> responsePuntos = requestPuntos.execute();
        return responsePuntos.body();
    }
}
