package com.example.sneakers.util

import android.os.StrictMode
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

//CLASE QUE CONTROLA EL CONSUMO DE SERVICIO

class SoapService {

    //ELEMENTS OF THE SERVICE
    private val nameSpace = "http://tempuri.org/"
    private val url = "http://www.interfacesavanzadasp.somee.com/Service1.svc?singleWsdl"
    private var methodName = ""
    private var soapAction = ""


    //VARIABLE FOR THE RESPONSE OF THE SERVICE
    private var resultString: SoapPrimitive? = null
    private var response: String = ""

    //METODO QUE OPTIENE TODOS LOS PRODUCTOS
    fun getProducts ( ) : String{
        //SE ASIGNA EL NOMBRE DEL METODO Y EL SOAP ACTION
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
}