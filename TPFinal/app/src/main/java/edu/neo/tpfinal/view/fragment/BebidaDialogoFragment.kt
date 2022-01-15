package edu.neo.tpfinal.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Bebida
import edu.neo.tpfinal.viewmodel.BebidaViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BebidaDialogoFragment : DialogFragment() {
    private lateinit var bebidaViewModel: BebidaViewModel
    private lateinit var image: ImageView
    private lateinit var nombre: TextView
    private lateinit var categoria: TextView
    private lateinit var instruccion: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_bebida,null)
        val bebidaVM = ViewModelProvider(this).get(BebidaViewModel::class.java)
        Inicializar(view)
        bebidaVM.getBebida()
            .enqueue(object : Callback<Bebida> {
                override fun onResponse(call: Call<Bebida>, response: Response<Bebida>) {
                    if (response.body() != null) {
                        val data = response.body()
                        nombre.setText(data?.drinks?.get(0)?.strDrink)
                        categoria.setText("(${data?.drinks?.get(0)?.strCategory})")
                        instruccion.setText("Instruccion: ${data?.drinks?.get(0)?.strInstructions}")
                        Glide
                            .with(view.context)
                            .load(data?.drinks?.get(0)?.strDrinkThumb)
                            .centerInside()
                            .into(image)
                    }
                }

                override fun onFailure(call: Call<Bebida>, t: Throwable) {
                    Log.e("Error-Invoke-API", t.message.toString())
                }

            })


        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(view)
                .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener() {dialog, id ->

                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    fun Inicializar(view:View){
        image =  view.findViewById(R.id.beb_imagen)
        nombre = view.findViewById(R.id.beb_nombre)
        categoria = view.findViewById(R.id.beb_categoria)
        instruccion = view.findViewById(R.id.beb_instruccion)
    }
}