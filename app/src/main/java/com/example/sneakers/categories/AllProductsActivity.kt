package com.example.sneakers.categories

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import android.widget.ImageButton
import android.widget.TextView
import com.example.sneakers.R
import com.example.sneakers.models.Tennis
import com.example.sneakers.util.AdapterTennis
import com.example.sneakers.util.BottomNavigationViewHelper
import com.example.sneakers.util.SoapService

class AllProductsActivity : AppCompatActivity() {
    private var listOfTennis : ArrayList <Tennis> ?= null
    private val context = this
    private val numberOfActivity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        val imageButtonBack : ImageButton = findViewById(R.id.imageButtonBack)
        val textViewCategories : TextView = findViewById(R.id.textViewCategories)
        var gridViewProducts = findViewById <GridView> (R.id.gridViewProducts)
        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNaViewBar)

        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)

        val intent = intent
        val category = intent.getStringExtra("Category")
        if(category == "0"){
            showAllProducts()
        }else{
            when(category){
                "1" -> { textViewCategories.text = "HOMBRES" }
                "2" -> { textViewCategories.text = "MUJERES" }
                "3" -> { textViewCategories.text = "NIÑOS" }
                "4" -> { textViewCategories.text = "NIÑAS" }
            }
            showProductsForCategory(category)
        }
        val adapter = AdapterTennis(this,listOfTennis!!)
        gridViewProducts.adapter = adapter

        imageButtonBack.setOnClickListener{
            val intent = Intent(this,CategoriesActivity::class.java)
            startActivity(intent)
        }
    }


   //Metodo para ver los productos , aqui se crea la lista de objetos
    private fun showAllProducts() {
       val productsString = SoapService().getAllProducts()
       val products = productsString.split("\n")

       //Metodo para extraer los tennis de la string
       var i = 0
       listOfTennis = ArrayList()
       while (i <= products.size - 2) {
           var id = products[i++]
           var name = products[i++]
           var brand = products[i++]
           var price = products[i++]
           var objective = products[i++]
           var description = products[i++]
           var purchasePrice = products[i++]
           listOfTennis?.add(Tennis(id, name, brand, price, objective, description, purchasePrice, extractImages(id)))
       }
   }

    private fun showProductsForCategory(category: String){
        val productsString = SoapService().getProductsForCategory(category)
        val products = productsString.split("\n")

        //Metodo para extraer los tennis de la string
        var i = 0
        listOfTennis = ArrayList()
        while (i <= products.size - 2) {
            var id = products[i++]
            var name = products[i++]
            var brand = products[i++]
            var price = products[i++]
            var objective = products[i++]
            var description = products[i++]
            var purchasePrice = products[i++]
            listOfTennis?.add(Tennis(id, name, brand, price, objective, description, purchasePrice, extractImages(id)))
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