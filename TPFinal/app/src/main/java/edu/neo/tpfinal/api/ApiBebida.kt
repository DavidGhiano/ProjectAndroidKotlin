package edu.neo.tpfinal.api

import edu.neo.tpfinal.model.Bebida
import retrofit2.Call
import retrofit2.http.GET


interface ApiBebida {
    @GET("api/json/v1/1/random.php")
    fun getBebida():Call<Bebida>
}