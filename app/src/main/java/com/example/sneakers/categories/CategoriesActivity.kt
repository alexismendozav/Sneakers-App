package com.example.sneakers.categories

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.sneakers.R
import com.example.sneakers.util.BottomNavigationViewHelper
import com.example.sneakers.util.SoapService

class CategoriesActivity : AppCompatActivity() {
    private val context = this
    private val numberOfActivity = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        //SE ENLAZA LA BARRA DE NAVEGACION
        val bottomNavigationView : BottomNavigationView = context.findViewById(R.id.bottomNaViewBar)
        //SE MANDA A LLAMAR A LA CLASE QUE AYUDA A LA NAVEGACION
        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)

        showProducts()
    }

    private fun showProducts(){
        val products = SoapService().getProducts()
        val textViewShowProducts : TextView = findViewById(R.id.textViewShowProducts)
        textViewShowProducts.text=products
    }


}