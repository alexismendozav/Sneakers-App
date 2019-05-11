package com.example.sneakers.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.GridView
import com.example.sneakers.R
import com.example.sneakers.models.Tennis
import com.example.sneakers.util.AdapterTennis
import com.example.sneakers.util.BottomNavigationViewHelper
import com.example.sneakers.util.SoapService

class MainActivity : AppCompatActivity() {
    private val context = this
    private val numberOfActivity = 0
    private var listOfTennis : ArrayList <Tennis> ?= null
    private var listOfTennis2 : ArrayList <Tennis> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //SE ENLAZA LA BARRA DE NAVEGACION
        val bottomNavigationView : BottomNavigationView = context.findViewById(R.id.bottomNaViewBar)
        //SE MANDA A LLAMAR A LA CLASE QUE AYUDA A LA NAVEGACION
        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)
        listOfTennis = ArrayList()
        listOfTennis2 = ArrayList()
        showProductsForCategory("2",listOfTennis!!)
        showProductsForCategory("4",listOfTennis2!!)

        var gridViewHome = findViewById <GridView> (R.id.gridViewHome)
        var gridViewHome2 = findViewById <GridView> (R.id.gridViewHome2)
        val adapter = AdapterTennis(this,listOfTennis!!)
        gridViewHome.adapter = adapter

        val adapter1 = AdapterTennis(this,listOfTennis2!!)
        gridViewHome2.adapter = adapter1

    }


    private fun showProductsForCategory(category: String,list : ArrayList<Tennis>){
        val productsString = SoapService().getProductsForCategory(category)
        val products = productsString.split("\n")

        //Metodo para extraer los tennis de la string
        var i = 0

        while (i <= products.size - 2) {
            var id = products[i++]
            var name = products[i++]
            var brand = products[i++]
            var price = products[i++]
            var objective = products[i++]
            var description = products[i++]
            var purchasePrice = products[i++]
            list?.add(Tennis(id, name, brand, price, objective, description, purchasePrice, extractImages(id)))
        }
    }

    private fun extractImages(id : String) : Int{
        when(id){
            "916780-403" -> return R.drawable.a403
            "VN001" -> return R.drawable.vn0001
            "916783-103" -> return R.drawable.a103
            "922859-602" -> return R.drawable.a602
            "922885-601" -> return R.drawable.a601
            "3020087-100" -> return R.drawable.a100
            "AA0544-001" -> return R.drawable.a001
            "AA2146-104" -> return R.drawable.a104
            "AH3481-400" -> return R.drawable.a400
            "AH5226-002" -> return R.drawable.a002
            "AH5554-400" -> return R.drawable.a401
            "AH5556-400" -> return R.drawable.a402
            "AO1686-007" -> return R.drawable.a007
            "AW4294" -> return R.drawable.aw4224
            "B37731" -> return R.drawable.b37
            "B42100" -> return R.drawable.aa
            "B75701" -> return R.drawable.b75
            "B79771" -> return R.drawable.b79
            "BB5478" -> return R.drawable.bb54
            "BB7435" -> return R.drawable.bb74
            "BB9797" -> return R.drawable.bb97
            "CQ2972" -> return R.drawable.cq
            "DB0836" -> return R.drawable.db0
            "VN0A38EMU5D" -> return R.drawable.vn0
            else -> return  R.drawable.vn0001
        }

    }
}
