package com.example.sneakers.user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sneakers.R
import com.example.sneakers.util.SoapService
import kotlinx.android.synthetic.main.activity_regitro.*
import kotlinx.android.synthetic.main.activity_sesion.*
import org.w3c.dom.Text
import java.util.concurrent.ThreadLocalRandom

class Registry : AppCompatActivity() {

    //UI Variables
    private var textViewTittle   : TextView ?= null
    private var editTextName     : EditText ?= null
    private var editTextLastName : EditText ?= null
    private var editTextEmail    : EditText ?= null
    private var editTextPhone    : EditText ?= null
    private var editTextCp       : EditText ?= null
    private var editTextCountry  : EditText ?= null
    private var editTextCity     : EditText ?= null
    private var editTextColony   : EditText ?= null
    private var editTextStreet   : EditText ?= null
    private var editTextPassword : EditText ?= null
    private var editTextVerifyPassword : EditText ?= null
    private var buttonRegistry   : Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regitro)

        textViewTittle   = findViewById(R.id.tittleRegister)
        editTextName     = findViewById(R.id.inputName)
        editTextLastName = findViewById(R.id.inputLastName)
        editTextEmail    = findViewById(R.id.inputEmailRegister)
        editTextPhone    = findViewById(R.id.inputPhone)
        editTextCp       = findViewById(R.id.inputCp)
        editTextCountry  = findViewById(R.id.inputEstate)
        editTextCity     = findViewById(R.id.inputCity)
        editTextColony   = findViewById(R.id.inputColony)
        editTextStreet   = findViewById(R.id.inputColony)
        editTextPassword =findViewById(R.id.inputPasswordRegister)
        editTextVerifyPassword = findViewById(R.id.inputVerifyPassword)
        buttonRegistry   = findViewById(R.id.btnRegisterUser)

        btnRegisterUser.setOnClickListener{

            var correctData =SoapService().setDataUser(getId(),editTextName?.text.toString(),
                                         editTextLastName?.text.toString(),editTextEmail?.text.toString(),
                                         editTextPhone?.text.toString(),editTextCp?.text.toString(),
                                         editTextCountry?.text.toString(),editTextCity?.text.toString(),
                                         editTextColony?.text.toString(),editTextStreet?.text.toString(),
                                         editTextPassword?.text.toString())
            if(correctData == "1"){
                editTextName?.setText("")
                editTextLastName?.setText("")
                editTextEmail?.setText("")
                editTextPhone?.setText("")
                editTextCp?.setText("")
                editTextCountry?.setText("")
                editTextCity?.setText("")
                editTextColony?.setText("")
                editTextStreet?.setText("")
                editTextPassword?.setText("")
                editTextVerifyPassword?.setText("")
                val intent = Intent(this,Session::class.java)
                intent.putExtra("session","correctSession")
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Algo salio mal, intentelo mas tarde",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getId() :Int{
        return  (10000..999999).shuffled().last()
    }
}
