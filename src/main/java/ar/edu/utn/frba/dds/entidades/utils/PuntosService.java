package ar.edu.utn.frba.dds.entidades.utils;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PuntosService {
    @GET("puntos")
    Call<ListadoDePuntos> puntos();
}
