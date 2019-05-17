package com.example.sneakers.util

import android.os.StrictMode
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

//CLASE QUE CONTROLA EL CONSUMO DE SERVICIO

class SoapService {
    //Elementos que se usan para el servicio
    private val nameSpace = "http://tempuri.org/"
    private val url = "http://www.interfacesavanzadasp.somee.com/Service1.svc?singleWsdl"
    private var methodName = ""
    private var soapAction = ""
    //Otras variables
    private var resultString: SoapPrimitive? = null
    private var response: String = ""
    //Metodo que optiene todos los productos
    fun getAllProducts ( ) : String{
        methodName = "GetProductos_String"
        soapAction = "http://tempuri.org/IService1/GetProductos_String"
        try {
            val request = SoapObject(nameSpace, methodName)
            val soapEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            soapEnvelope.dotNet = true
            soapEnvelope.setOutputSoapObject(request)
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val transport = HttpTransportSE(url)
            transport.call(soapAction, soapEnvelope)
            resultString = soapEnvelope.response as SoapPrimitive
            response =  resultString.toString()
        } catch (ex: Exception) {
            response = "Error\n$ex"
        }
        return  response
    }

    fun getProductsForCategory(category: String) : String{
        //SE ASIGNA EL NOMBRE DEL METODO Y EL SOAP ACTION
        var objective = category.toInt()
        methodName = "GetCategoria"
        soapAction = "http://tempuri.org/IService1/GetCategoria"
        try {
            val request = SoapObject(nameSpace, methodName)
            //Se agrega el parametro que necesita el metodo
            request.addProperty("objetivo",objective)
            val soapEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            soapEnvelope.dotNet = true
            soapEnvelope.setOutputSoapObject(request)
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val transport = HttpTransportSE(url)
            transport.call(soapAction, soapEnvelope)
            resultString = soapEnvelope.response as SoapPrimitive
            response =  resultString.toString()
        } catch (ex: Exception) {
            response = "Error\n$ex"
        }
        return  response
    }

    fun getProductsForSearch(search : String) : String{
        //SE ASIGNA EL NOMBRE DEL METODO Y EL SOAP ACTION
        var objective = search
        methodName = "GetBusquedaStr"
        soapAction = "http://tempuri.org/IService1/GetBusquedaStr"
        try {
            val request = SoapObject(nameSpace, methodName)
            //Se agrega el parametro que necesita el metodo
            request.addProperty("objetivo",objective)
            val soapEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            soapEnvelope.dotNet = true
            soapEnvelope.setOutputSoapObject(request)
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val transport = HttpTransportSE(url)
            transport.call(soapAction, soapEnvelope)
            resultString = soapEnvelope.response as SoapPrimitive
            response =  resultString.toString()
        } catch (ex: Exception) {
            response = ""
        }
        return  response
    }

    fun getStartSession(email : String, password : String ) : String{
        //SE ASIGNA EL NOMBRE DEL METODO Y EL SOAP ACTION
        var email = email
        var password = password
        methodName = "VerificarLogin"
        soapAction = "http://tempuri.org/IService1/VerificarLogin"
        try {
            val request = SoapObject(nameSpace, methodName)
            //Se agrega el parametro que necesita el metodo
            request.addProperty("correo",email)
            request.addProperty("contrasena",password)
            val soapEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            soapEnvelope.dotNet = true
            soapEnvelope.setOutputSoapObject(request)
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val transport = HttpTransportSE(url)
            transport.call(soapAction, soapEnvelope)
            resultString = soapEnvelope.response as SoapPrimitive
            response =  resultString.toString()
        } catch (ex: Exception) {
            response = "0"
        }
        return  response
    }

    fun setDataUser(id_cliente: Int,    nombre: String,
                    apellido  : String, email : String,
                    telefono  : String, cp    : String,
                    estado    : String, ciudad: String,
                    colonia   : String, calle : String, password  : String): String{

        methodName = "AgregarCliente"
        soapAction = "http://tempuri.org/IService1/AgregarCliente"
        try {
            val request = SoapObject(nameSpace, methodName)
            //Se agrega el parametro que necesita el metodo
            request.addProperty("id_cliente",id_cliente)
            request.addProperty("nombre",nombre)
            request.addProperty("apellidos",apellido)
            request.addProperty("correo",email)
            request.addProperty("telefono",telefono)
            request.addProperty("codigo_postal",cp)
            request.addProperty("estado",estado)
            request.addProperty("ciudad",ciudad)
            request.addProperty("colonia",colonia)
            request.addProperty("calle",calle)
            request.addProperty("contrasena",password)
            val soapEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            soapEnvelope.dotNet = true
            soapEnvelope.setOutputSoapObject(request)
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val transport = HttpTransportSE(url)
            transport.call(soapAction, soapEnvelope)
            resultString = soapEnvelope.response as SoapPrimitive
            response =  resultString.toString()
        } catch (ex: Exception) {
            response = "0"
        }
        return  response
    }


    fun getClient(email : String) : String{
        //SE ASIGNA EL NOMBRE DEL METODO Y EL SOAP ACTION
        methodName = "GetCliente"
        soapAction = "http://tempuri.org/IService1/GetCliente"
        try {
            val request = SoapObject(nameSpace, methodName)
            //Se agrega el parametro que necesita el metodo
            request.addProperty("correo",email)
            val soapEnvelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            soapEnvelope.dotNet = true
            soapEnvelope.setOutputSoapObject(request)
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val transport = HttpTransportSE(url)
            transport.call(soapAction, soapEnvelope)
            resultString = soapEnvelope.response as SoapPrimitive
            response =  resultString.toString()
        } catch (ex: Exception) {
            response = ""
        }
        return  response
    }

}