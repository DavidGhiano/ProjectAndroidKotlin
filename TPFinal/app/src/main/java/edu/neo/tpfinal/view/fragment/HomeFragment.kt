package edu.neo.tpfinal.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import edu.neo.tpfinal.R
import edu.neo.tpfinal.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private val PREFERENCE_FILE_KEY: String = "tpfinal.view"
    private val ESTADO_USUARIO: String = "estado.usuario.activo"
    private lateinit var homeVM: HomeViewModel
    private lateinit var saludos:TextView
    private lateinit var image:ImageView
    private lateinit var firebaseDB: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_home, container, false)
        val sharePref: SharedPreferences =  view.context.getSharedPreferences (PREFERENCE_FILE_KEY,
            AppCompatActivity.MODE_PRIVATE
        )
        val userID:String = sharePref.getString(ESTADO_USUARIO, "").toString()
        homeVM = ViewModelProvider(this).get(HomeViewModel::class.java)
        saludos = view.findViewById(R.id.home_saludo)
        image = view.findViewById(R.id.home_image)
        FirebaseApp.initializeApp(view.context)
        firebaseDB = FirebaseDatabase.getInstance()
        dbReference = firebaseDB.getReference()
        dbReference.child("Persona").child(userID).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        saludos.setText("¡Hola ${snapshot.child("nombre").getValue().toString()} ${snapshot.child("apellido").getValue().toString()}!")

                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )

        return view
    }
}