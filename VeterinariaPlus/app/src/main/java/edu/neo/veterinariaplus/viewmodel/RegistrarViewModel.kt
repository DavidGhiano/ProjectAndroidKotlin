package edu.neo.veterinariaplus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.neo.veterinariaplus.dao.DbHelper
import edu.neo.veterinariaplus.model.Mascota
import edu.neo.veterinariaplus.model.Medico
import java.time.LocalDate
import java.time.LocalDate.now
import java.util.*

class RegistrarViewModel : ViewModel() {
    fun registrarMascota(
        nombre: String,
        tipo: String,
        raza: String,
        doctor: String,
        edad: String,
        causaAtencion: String, context: Context
    ): Boolean {
        val db: DbHelper = DbHelper(context, null)
        return db.registrarMascota(
            Mascota(
                0,
                nombre,
                tipo,
                raza,
                doctor,
                edad,
                getFecha(),
                causaAtencion
            )
        )
    }

    fun contarMedicoDia(context: Context): ArrayList<Medico> {
        val db:DbHelper = DbHelper(context,null)
        return db.contarMedicoDia(getFecha())
    }


    fun getFecha():String{
        return now().toString().replace("-", "")
    }
}