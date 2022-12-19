package com.example.animalsapi.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animalsapi.R
import com.example.animalsapi.data.Animal

class RecyclerAdapter(private val animalsList: ArrayList<Animal>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = animalsList[position]
        holder.name.text = "Name: ${currentItem.name}"
        holder.type.text = "Type: ${currentItem.animal_type}"
        holder.lifespan.text = "LifeSpan: ${currentItem.lifespan}"
        initImg(holder.image.context, currentItem.image_link, holder.image)
    }

    override fun getItemCount() = animalsList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var name: TextView = itemView.findViewById(R.id.name)
        var type: TextView = itemView.findViewById(R.id.type)
        var lifespan: TextView = itemView.findViewById(R.id.lifeSpan)
    }

    private fun initImg(ctx: Context, link: String?, image: ImageView) {
        Glide.with(ctx).load(link).into(image)
    }
}