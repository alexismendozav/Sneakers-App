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

//Clase que lelva el control de la barra de navegación

class BottomNavigationViewHelper {
    private var context : Context ?= null
    fun setupBottomNavigationView (numberOfActivity : Int , context: Context, bottomNavigationView : BottomNavigationView) {
        this.context = context
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //Asigna el color al icono de la barra al que se dio clic
        val menu : Menu = bottomNavigationView.menu
        val menuItem : MenuItem = menu.getItem(numberOfActivity)
        menuItem.isChecked = true
    }

    //Maneja que hacer cuando se le da clic a cada boton de la barra de navegación
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        //Se switchea los el id de cada icono en la barra
        when (item.itemId) {
            R.id.ic_house -> {
                // Se muestra la ventana correspondiente
                val intent = Intent (context, MainActivity::class.java)
                //Se elimina la pila de activities
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context?.startActivity(intent)

            }
            R.id.ic_categories -> {
                val intent2 = Intent(context, CategoriesActivity::class.java)
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context?.startActivity(intent2)
            }
            R.id.ic_search -> {
                val intent3 = Intent(context, SearchActivity::class.java)
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context?.startActivity(intent3)
            }
            R.id.ic_user -> {
                val intent4 = Intent(context, UserActivity::class.java)
                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context?.startActivity(intent4)
            }
        }
        false
    }
}

