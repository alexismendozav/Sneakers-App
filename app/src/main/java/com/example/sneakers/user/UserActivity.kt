package com.example.sneakers.user

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.sneakers.R
import com.example.sneakers.util.BottomNavigationViewHelper

class UserActivity : AppCompatActivity() {
    private val context = this
    private val numberOfActivity = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        //SE ENLAZA LA BARRA DE NAVEGACION
        val bottomNavigationView : BottomNavigationView = context.findViewById(R.id.bottomNaViewBar)
        //SE MANDA A LLAMAR A LA CLASE QUE AYUDA A LA NAVEGACION
        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)
    }

}