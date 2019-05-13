package com.example.sneakers.user

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.AppCompatButton
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sneakers.R
import com.example.sneakers.util.SoapService

class Session : AppCompatActivity() {

    private var editTextEmail : EditText ?= null
    private var editTextPassword : EditText ?= null
    private var buttonStarSession : AppCompatButton ?= null
    private var textViewCreateAccount : TextView ?= null
    private var textViewRegistry : TextView ?= null

    private val key = "session"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonStarSession = findViewById(R.id.btnStartSession)
        textViewCreateAccount = findViewById(R.id.textViewCreateAccount)
        textViewRegistry = findViewById(R.id.textViewRegistry)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        //Extraer datos enviados desde otra activity
        val bundle=intent.extras
        if(bundle!=null)
        {
            textViewCreateAccount?.visibility = View.INVISIBLE
            textViewRegistry?.text = "Registro exitoso proceda a iniciar sesion"
        }

        buttonStarSession?.setOnClickListener{
            textViewRegistry?.visibility = View.INVISIBLE
            var email:String= editTextEmail?.text.toString()
            var password : String = editTextPassword?.text.toString()
            var correctData = SoapService().getStartSession(email, password )

            if(correctData == "1"){
                //Modo edicion del preference manager
                val editor = preferences.edit()
                editor.putString(key,email)
                editor.apply()
                val intent = Intent(this,UserActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                editTextPassword?.setText("")
                Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show()
            }
        }

        textViewCreateAccount?.setOnClickListener{
            var intent= Intent (this, Registry::class.java)
            startActivity(intent)
        }
    }
}
