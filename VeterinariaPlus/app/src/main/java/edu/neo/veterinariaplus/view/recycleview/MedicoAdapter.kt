package edu.neo.veterinariaplus.view.recycleview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.neo.veterinariaplus.R
import edu.neo.veterinariaplus.model.Mascota
import edu.neo.veterinariaplus.model.Medico
import edu.neo.veterinariaplus.viewmodel.EstadistivasViewModel

class MedicoAdapter(val lista: ArrayList<Medico>, val estadiVM: EstadistivasViewModel) :
    RecyclerView.Adapter<MedicoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medico_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: MedicoAdapter.ViewHolder, position: Int) {
        holder.nombre.text = "Medico: ${lista[position].nombre} - "
        holder.cantidad.text = "Cant de mascotas atendidas: ${lista[position].cant.toString()}"

        holder.verMas.setOnClickListener(
            View.OnClickListener {
                if (holder.verMas.text.equals("Ver más")) {
                    holder.rv_verMas.layoutManager =
                        LinearLayoutManager(it.context, LinearLayout.VERTICAL, false)
                    holder.rv_verMas.adapter = MedicoMascotaAdapter(
                        estadiVM.getAllMascotaDoc(
                            it.context,
                            lista[position].nombre
                        )
                    )
                    holder.verMas.setText("Ver menos")
                }else{
                    holder.rv_verMas.adapter = MedicoMascotaAdapter(ArrayList<Mascota>())
                    holder.verMas.setText("Ver más")
                }
            }
        )

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nombre:TextView
        var cantidad:TextView
        var verMas:Button
        var rv_verMas:RecyclerView

        init {
            nombre = view.findViewById(R.id.doc_nombre)
            cantidad = view.findViewById(R.id.doc_cantidad)
            verMas = view.findViewById(R.id.doc_ver)
            rv_verMas = view.findViewById(R.id.doc_rv_vermas)
        }

    }
}