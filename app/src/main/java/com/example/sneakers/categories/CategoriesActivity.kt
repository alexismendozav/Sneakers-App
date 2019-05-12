package com.example.sneakers.categories

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.sneakers.R
import com.example.sneakers.models.Category
import com.example.sneakers.models.Tennis
import com.example.sneakers.util.AdapterCategory
import com.example.sneakers.util.AdapterTennis
import com.example.sneakers.util.BottomNavigationViewHelper
import com.example.sneakers.util.SoapService

class CategoriesActivity : AppCompatActivity() {
    private val context = this
    private val numberOfActivity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        val bottomNavigationView : BottomNavigationView = context.findViewById(R.id.bottomNaViewBar)
        //Se manda a llamar al metodo que habilita la navegacion
        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerCategory)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        var categories = ArrayList<Category>()

        categories.add(Category("Todos",R.drawable.all))
        categories.add(Category("Hombres",R.drawable.men))
        categories.add(Category("Mujeres",R.drawable.women))
        categories.add(Category("Niños",R.drawable.childrens))
        categories.add(Category("Niñas",R.drawable.girl))

        val adapter = AdapterCategory(categories,this)
        recyclerView.adapter = adapter

    }
}