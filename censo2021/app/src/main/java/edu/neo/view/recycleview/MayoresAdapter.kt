package edu.neo.view.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.neo.R
import edu.neo.model.Persona

class MayoresAdapter(val items: ArrayList<Persona>) :
    RecyclerView.Adapter<MayoresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MayoresAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.mayores_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MayoresAdapter.ViewHolder, position: Int) {
        holder.nom_apellido.text =
            items[position].apellido.toUpperCase() + ", " + items[position].nombre
        holder.numDoc.text = items[position].numDoc.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nom_apellido: TextView
        var numDoc: TextView

        init {
            nom_apellido = view.findViewById(R.id.rv_est_apeNom)
            numDoc = view.findViewById(R.id.rv_est_dni)
        }
    }
}