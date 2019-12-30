package com.example.sekolah

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sekolah.ui.login.model.murid.DataItem
import kotlinx.android.synthetic.main.activity_murid_adapter.view.*

class Murid_Adapter(
    var context: Context,
    var list: List<DataItem>,
    private var onItemClickListener: OnmuridClickListener//fungsi delete
) :
    RecyclerView.Adapter<Murid_Adapter.ViewHolder>() {
    lateinit var iduser: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View
        v = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_murid_adapter, parent, false)
        return ViewHolder(v,onItemClickListener)//delete

    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: Murid_Adapter.ViewHolder, position: Int) {
        val item = list.get(position)
        holder.namaMurid.text = item.namaMurid
        holder.jk.text = item.jk
        holder.kelas.text = item.kelas
        holder.idMurid.text = item.idMurid
        holder.alamat.text = item.alamat

        //delete murid
        holder.onBindviewMurid(list[position], position)

    }

    class ViewHolder(
        itemView: View,
        private val onItemClickListener: OnmuridClickListener//delete
    ) : RecyclerView.ViewHolder(itemView) {


        var idMurid = itemView.tv_id_murid
        var namaMurid = itemView.tv_nama_murid
        var jk = itemView.tv_jk
        var kelas = itemView.tv_kelas
        var alamat = itemView.tv_alamat

        fun onBindviewMurid(//delete
            list: DataItem,
            position: Int
        ) {
            //pilih setOnclikListener yang atas sendiri (View.setOnClickListener?)
           itemView.setOnClickListener{
               onItemClickListener.onClick(list, position)
           }
        }
    }

    //aksi tiap klik buat interface, ini klik di recylerviewnya
    interface OnmuridClickListener {
        fun onClick(list: DataItem, position: Int)
    }

}


