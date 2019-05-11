package com.example.sneakers.util

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sneakers.R
import com.example.sneakers.categories.AllProductsActivity
import com.example.sneakers.models.Category
import com.example.sneakers.search.SearchActivity


class AdapterCategory (var list: ArrayList<Category>, context : Context) : RecyclerView.Adapter<AdapterCategory.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_category,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun onBindViewHolder(holder: AdapterCategory.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindItems(data:Category){
            val title:TextView = itemView.findViewById(R.id.textViewTitle)
            val thumbnail:ImageView = itemView.findViewById(R.id.thumbnail)

            title.text = data.titulo
            Glide.with(itemView.context).load(data.thumbnail).into(thumbnail)

            itemView.setOnClickListener{
                if(data.titulo == "Todos"){
                    val intent = Intent(itemView.context,AllProductsActivity::class.java)
                    intent.putExtra("Category","0")
                    itemView.context.startActivity(intent)
                }
                if(data.titulo == "Hombres"){
                    val intent = Intent(itemView.context,AllProductsActivity::class.java)
                    intent.putExtra("Category","1")
                    itemView.context.startActivity(intent)
                }
                if(data.titulo == "Mujeres"){
                    val intent = Intent(itemView.context,AllProductsActivity::class.java)
                    intent.putExtra("Category","2")
                    itemView.context.startActivity(intent)
                }
                if(data.titulo == "Niños"){
                    val intent = Intent(itemView.context,AllProductsActivity::class.java)
                    intent.putExtra("Category","3")
                    itemView.context.startActivity(intent)
                }
                if(data.titulo == "Niñas"){
                    val intent = Intent(itemView.context,AllProductsActivity::class.java)
                    intent.putExtra("Category","4")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}