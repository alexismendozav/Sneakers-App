package com.example.sneakers.util

import android.content.Context
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.sneakers.*
import com.example.sneakers.categories.CategoriesActivity
import com.example.sneakers.home.MainActivity
import com.example.sneakers.search.SearchActivity
import com.example.sneakers.user.UserActivity

//CLASE QUE LLEVA EL CONTROL DE LA BARRA DE NAVEGACIÓN

class BottomNavigationViewHelper () {
    private var context : Context ?= null
    fun setupBottomNavigationView (numberOfActivity : Int , context: Context, bottomNavigationView : BottomNavigationView) {
        this.context = context
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //ASIGNA EL COLOR AL ICONO AL QUE SE DIO CLIC
        val menu : Menu = bottomNavigationView.menu
        val menuItem : MenuItem = menu.getItem(numberOfActivity)
        menuItem.isChecked = true
    }

    //MANEJA EL CLIC EN LA BARRA DE NAVEGACION
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        //SE SWITCHEA LOS ICONOS DE LA BARRA DE NAVEGACIÓN
        when (item.itemId) {
            R.id.ic_house -> {
                val intent = Intent (context, MainActivity::class.java)
                context?.startActivity(intent)
                
            }
            R.id.ic_categories -> {
                val intent2 = Intent(context, CategoriesActivity::class.java)
                context?.startActivity(intent2)
            }
            R.id.ic_search -> {
                val intent3 = Intent(context, SearchActivity::class.java)
                context?.startActivity(intent3)
            }
            R.id.ic_user -> {
                val intent4 = Intent(context, UserActivity::class.java)
                context?.startActivity(intent4)
            }
        }
        false
    }
}

