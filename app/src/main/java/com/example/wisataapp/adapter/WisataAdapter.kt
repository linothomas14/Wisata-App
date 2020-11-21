package com.example.wisataapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wisataapp.R
import com.example.wisataapp.detail.DetailWisataActivity
import com.example.wisataapp.model.Wisata
import kotlinx.android.synthetic.main.item_wisata.view.*


class WisataAdapter(var data: ArrayList<Wisata>?) :
    RecyclerView.Adapter<WisataAdapter.WisataHolder>() {
    class WisataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.itemImg
        val textName = itemView.itemNamaLokasi
        val itemNamaTempat = itemView.itemNamaTempat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)
        //this code is to get layout

        val holder = WisataHolder(view)
        //and make a var named holder that contain a WisataHolder with the parameter type is View
        return holder
    }

    override fun onBindViewHolder(holder: WisataHolder, position: Int) {
        holder.itemNamaTempat.text = data?.get(position)?.nama_tempat
        holder.textName.text = data?.get(position)?.lokasi

        Glide.with(holder.itemView.context)
            .load(data?.get(position)?.gambar)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.img)

        //Action CLick
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailWisataActivity::class.java)
            intent.putExtra("jdl", data?.get(position)?.nama_tempat)
            intent.putExtra("img", data?.get(position)?.gambar)
            intent.putExtra("desk", data?.get(position)?.deskripsi)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        //to setting how many data will show on our layout, in this case we just set based on how many 'data' is,
        //so we can set this to data.size and dont forget to add the nullsafe
        return data?.size ?: 0
    }
}