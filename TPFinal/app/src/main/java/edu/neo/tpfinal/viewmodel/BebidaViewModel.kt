package edu.neo.tpfinal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.neo.tpfinal.api.implementation.ApiBebidaImp
import edu.neo.tpfinal.model.Bebida
import retrofit2.Call

class BebidaViewModel : ViewModel() {
    fun getBebida(): Call<Bebida>{
        val api: ApiBebidaImp = ApiBebidaImp()
        return api.getBebidaRandom()
    }

}