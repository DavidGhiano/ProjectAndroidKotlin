package edu.neo.tpfinal.db

import android.content.Context
import android.renderscript.Sampler
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import edu.neo.tpfinal.model.Dieta
import edu.neo.tpfinal.model.Persona
import edu.neo.tpfinal.model.User
import java.lang.Exception
import java.util.logging.Logger.global
import kotlin.coroutines.coroutineContext

class dbFireBase() {
    lateinit var firebaseDB: FirebaseDatabase
    lateinit var dbReference: DatabaseReference

    fun InicializarDB(context: Context) {
        FirebaseApp.initializeApp(context)
        firebaseDB = FirebaseDatabase.getInstance()
        dbReference = firebaseDB.getReference()
    }

    fun InsertPersona(persona: Persona): Boolean {
        return try {
            dbReference.child("Persona").child(persona.id.toString().toLowerCase())
                .setValue(persona)
            true
        } catch (e: Exception) {
            Log.i("Error en BD", e.message.toString())
            false
        }
    }

    fun InsertDieta(dieta: Dieta,userID:String):Boolean{
        return try{
            dbReference
                .child("Persona")
                .child(userID)
                .child("dieta")
                .push().setValue(dieta)
            true
        }catch (e:Exception){
            Log.e("Error DB: ",  e.message.toString())
            false
        }

    }
}