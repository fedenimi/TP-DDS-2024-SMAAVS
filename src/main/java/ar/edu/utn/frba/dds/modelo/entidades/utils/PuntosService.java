package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.dtos.PuntoDonacionCreate;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import org.json.JSONArray;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface PuntosService {
    @GET("recomendaciones")
    Call<List<Punto>> puntos(@Query("lat") Double latitud, @Query("long") Double longitud, @Query("limit") Integer limit);

   // @POST("puntos")
   // Call<List<PuntoDonacionCreate>> agregarPunto(@Query("nombre") String nombre, @Query("lat") Double latitud, @Query("long") Double longitud, @Query("direccion") String direccion);

    @POST("puntos")
    Call<List<PuntoDonacionCreate>> agregarPunto(@Body List<PuntoDonacionCreate> puntos);
}

