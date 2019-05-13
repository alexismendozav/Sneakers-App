package com.example.sneakers.user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.sneakers.R
import com.example.sneakers.util.SoapService
import kotlinx.android.synthetic.main.activity_registry.*
import java.util.regex.Pattern

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

    private var correctData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        textViewTittle   = findViewById(R.id.tittleRegister)
        editTextName     = findViewById(R.id.inputName)
        editTextLastName = findViewById(R.id.inputLastName)
        editTextEmail    = findViewById(R.id.inputEmailRegister)
        editTextPhone    = findViewById(R.id.inputPhone)
        editTextCp       = findViewById(R.id.inputCp)
        editTextCountry  = findViewById(R.id.inputEstate)
        editTextCity     = findViewById(R.id.inputCity)
        editTextColony   = findViewById(R.id.inputColony)
        editTextStreet   = findViewById(R.id.inputStreet)
        editTextPassword =findViewById(R.id.inputPasswordRegister)
        editTextVerifyPassword = findViewById(R.id.inputVerifyPassword)
        buttonRegistry   = findViewById(R.id.btnRegisterUser)

        btnRegisterUser.setOnClickListener{
            if(verifyData()){
                correctData =SoapService().setDataUser(getId(),editTextName?.text.toString(),
                    editTextLastName?.text.toString(),editTextEmail?.text.toString(),
                    editTextPhone?.text.toString(),editTextCp?.text.toString(),
                    editTextCountry?.text.toString(),editTextCity?.text.toString(),
                    editTextColony?.text.toString(),editTextStreet?.text.toString(),
                    editTextPassword?.text.toString())
            }

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

            }
        }
    }

    private fun getId() :Int{
        return  (10000..999999).shuffled().last()
    }

    private fun verifyData():Boolean{
        var name = false
        var lastName = false
        var email = false
        var phone = false
        var cp = false
        var country = false
        var city = false
        var colony = false
        var street = false
        var password = false
        var verifyPassword = false

        //Verificar que el nombre sea correcto
        if(!editTextName?.text.isNullOrEmpty()) {
            if(Pattern.compile("[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]*").matcher(editTextName?.text).matches())
            {
                name = true
            }else
            {
                editTextName?.error = "Digite un nombre valido"
                name = false
            }
        }else
        {
            editTextName?.error = "Campo obligatorio"
            name = false
        }

        //Verificar que el apellido sea correcto
        if(!editTextLastName?.text.isNullOrEmpty()) {
            if(Pattern.compile("[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]*").matcher(editTextLastName?.text).matches())
            {
                lastName = true
            }else
            {
                editTextLastName?.error = "Digite un apellido valido"
                lastName = false
            }
        }else
        {
            editTextLastName?.error = "Campo obligatorio"
            lastName = false
        }

        //Verificar que el email es correcto
        if(!editTextEmail?.text.isNullOrEmpty()) {
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail?.text).matches())
            {
                email = true
            }else
            {
                editTextEmail?.error = "Digite un correo valido"
                email = false
            }
        }else
        {
            editTextEmail?.error = "Campo obligatorio"
            email = false
        }

        //Verificar telefono
        if(!editTextPhone?.text.isNullOrEmpty()) {
            if(Pattern.compile("[0-9]*").matcher(editTextPhone?.text).matches())
            {
                phone = true
            }else
            {
                editTextPhone?.error = "Digite un telefono valido"
                phone = false
            }
        }else
        {
            editTextPhone?.error = "Campo obligatorio"
            phone = false
        }

        //Verificar codigo postal
        if(!editTextCp?.text.isNullOrEmpty()) {
            if(Pattern.compile("[0-9]*").matcher(editTextCp?.text).matches())
            {
                cp = true
            }else
            {
                editTextCp?.error = "Digite un codigo postal valido"
                cp = false
            }
        }else
        {
            editTextCp?.error = "Campo obligatorio"
            cp = false
        }

        //Verificar estado
        if(!editTextCountry?.text.isNullOrEmpty()) {
            if(Pattern.compile("[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]*").matcher(editTextCountry?.text).matches())
            {
                country = true
            }else
            {
                editTextCountry?.error = "Digite el nombre de su estado"
                country = false
            }
        }else
        {
            editTextCountry?.error = "Campo obligatorio"
            country = false
        }

        //Verificar ciudad
        if(!editTextCity?.text.isNullOrEmpty()) {
            if(Pattern.compile("[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*").matcher(editTextCity?.text).matches())
            {
                city = true
            }else
            {
                editTextCity?.error = "Digite el nombre de su ciudad"
                city = false
            }
        }else
        {
            editTextCity?.error = "Campo obligatorio"
            city = false
        }

        //Verificar colonia
        if(!editTextColony?.text.isNullOrEmpty()) {
            if(Pattern.compile("[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]*").matcher(editTextColony?.text).matches())
            {
                colony = true
            }else
            {
                editTextColony?.error = "Digite el nombre de su ciudad"
                colony = false
            }
        }else
        {
            editTextColony?.error = "Campo obligatorio"
            colony = false
        }


        //Verificar calle
        if(!editTextStreet?.text.isNullOrEmpty()) {
            if(Pattern.compile("[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s#]*").matcher(editTextStreet?.text).matches())
            {
                street = true
            }else
            {
                editTextStreet?.error = "Digite el nombre de su calle"
                street = false
            }
        }else
        {
            editTextStreet?.error = "Campo obligatorio"
            street = false
        }

        //Verificar password
        if(!editTextPassword?.text.isNullOrEmpty()) {
           password = true
        }else
        {
            editTextPassword?.error = "Campo obligatorio"
            password = false
        }

        //Verificar password
        if(!editTextVerifyPassword?.text.isNullOrEmpty()) {

            if(editTextPassword?.text.toString() == editTextVerifyPassword?.text.toString()){
                verifyPassword = true
            }else{
                editTextVerifyPassword?.error = "Las contraseñas no coinciden"
                verifyPassword = false
            }

        }else
        {
            editTextVerifyPassword?.error = "Campo obligatorio"
            verifyPassword = false
        }

        return name && lastName && email && phone && cp && country && city && colony && street && password && verifyPassword
    }
}
