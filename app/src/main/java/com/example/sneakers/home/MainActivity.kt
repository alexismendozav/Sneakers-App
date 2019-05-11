package com.example.sneakers.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.example.sneakers.R
import com.example.sneakers.util.BottomNavigationViewHelper

class MainActivity : AppCompatActivity() {
    private val context = this
    private val numberOfActivity = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //SE ENLAZA LA BARRA DE NAVEGACION
        val bottomNavigationView : BottomNavigationView = context.findViewById(R.id.bottomNaViewBar)
        //SE MANDA A LLAMAR A LA CLASE QUE AYUDA A LA NAVEGACION
        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)
    }
}
