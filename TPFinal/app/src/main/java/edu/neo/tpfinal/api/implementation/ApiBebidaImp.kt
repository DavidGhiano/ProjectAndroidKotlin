package edu.neo.tpfinal.api.implementation

import edu.neo.tpfinal.api.ApiBebida
import edu.neo.tpfinal.model.Bebida
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBebidaImp {
    private fun getRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thecocktaildb.com/")
            .build()
    }

    fun getBebidaRandom():Call<Bebida>{
        return getRetrofit().create(ApiBebida::class.java).getBebida()
    }
}