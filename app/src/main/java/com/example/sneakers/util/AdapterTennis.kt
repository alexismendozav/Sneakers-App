package com.example.sneakers.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.sneakers.R
import com.example.sneakers.models.Tennis


class AdapterTennis (var context:Context, items : ArrayList<Tennis>) : BaseAdapter() {
    var items : ArrayList<Tennis> ?= null
    init{
        this.items = items
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var holder:ViewHolder ?= null
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.adapter_tennis,null)
            holder = ViewHolder(view)
            view.tag = holder
        }else{
            holder = view.tag as? ViewHolder
        }
        val item = items?.get(position) as? Tennis
        holder?.imageViewSneaker?.setImageResource(item?.image!!)
        holder?.textViewName?.text = item?.name
        holder?.textViewBrand?.text = item?.brand
        holder?.textViewPrice?.text = item?.price
        return view!!
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return items?.count()!!
    }

    private class ViewHolder(view:View){
        var imageViewSneaker : ImageView ?= null
        var textViewName : TextView ?= null
        var textViewBrand : TextView ?= null
        var textViewPrice : TextView ?= null

        init {
            imageViewSneaker = view.findViewById(R.id.imageViewSneaker)
            textViewName = view.findViewById(R.id.textViewName)
            textViewBrand = view.findViewById(R.id.textViewBrand)
            textViewPrice = view.findViewById(R.id.textViewPrice)
        }
    }
}