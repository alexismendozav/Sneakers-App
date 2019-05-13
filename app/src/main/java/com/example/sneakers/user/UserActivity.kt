package com.example.sneakers.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.sneakers.R
import com.example.sneakers.util.BottomNavigationViewHelper
import com.example.sneakers.util.SoapService

class UserActivity : AppCompatActivity() {

    private var imageViewUser : ImageView?= null
    private var textViewWelcome  : TextView?= null
    private var textViewName  : TextView?= null
    private var textViewEmail : TextView?= null
    private var textViewPhone : TextView?= null
    private var textViewCp    : TextView?= null
    private var textViewAddress    : TextView?= null
    private var buttonStartSession :  AppCompatButton ?= null
    private var buttonCloseSession :  AppCompatButton ?= null

    private val context = this
    private val numberOfActivity = 3
    private var sessionEmail : String ?= null

    private var dataClient : List<String> ?= null

    private val key = "session"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        imageViewUser = findViewById(R.id.imageViewUser)
        textViewWelcome = findViewById(R.id.textViewWelcome)
        textViewName = findViewById(R.id.textViewName)
        textViewEmail = findViewById(R.id.textViewEmail)
        textViewPhone = findViewById(R.id.textViewPhone)
        textViewCp = findViewById(R.id.textViewCp)
        textViewAddress = findViewById(R.id.textViewAddress)
        buttonStartSession = findViewById(R.id.btnSession)
        buttonCloseSession = findViewById(R.id.btnCloseSession)

        //Optenemos el preference manager
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        sessionEmail = preferences.getString(key,"Empty")

        if(sessionEmail != "Empty"){
            getDataClient(sessionEmail!!)
            imageViewUser?.setImageResource(R.drawable.usuario)
            textViewWelcome?.text = "BIENVENIDO"
            textViewName?.text = dataClient?.get(1) + " " +dataClient?.get(2)
            textViewEmail?.text = dataClient?.get(3)
            textViewPhone?.text= "Tel. " + dataClient?.get(4)
            textViewCp?.text = "C.P: " + dataClient?.get(5)
            buttonStartSession?.visibility = View.INVISIBLE

        }else{
            textViewEmail?.text = "HOLA VISITANTE"
            buttonStartSession?.visibility = View.VISIBLE
            buttonCloseSession?.visibility = View.INVISIBLE

        }

        //Barra de navegacion
        val bottomNavigationView : BottomNavigationView = context.findViewById(R.id.bottomNaViewBar)
        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)

        //Crear cuenta o iniciar sesion
        buttonStartSession?.setOnClickListener{
            var intent= Intent (context, Session::class.java)
            startActivity(intent)
        }

        //Cerrar sesion
        buttonCloseSession?.setOnClickListener{
            val editor = preferences.edit()
            editor.remove(key)
            editor.apply()
            val intent = Intent(this,UserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getDataClient(email : String){
        var data=SoapService().getClient(email)
        dataClient = data.split("\n")
    }
}