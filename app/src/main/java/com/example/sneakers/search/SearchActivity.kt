package com.example.sneakers.search

import android.app.ActionBar
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.example.sneakers.R
import com.example.sneakers.models.Tennis
import com.example.sneakers.util.AdapterTennis
import com.example.sneakers.util.BottomNavigationViewHelper
import com.example.sneakers.util.SoapService
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.layout_top_bar_search.*

class SearchActivity : AppCompatActivity() {
    private val context = this
    private val numberOfActivity = 2
    private var listOfTennis : ArrayList <Tennis> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.layout_top_bar_search)

        //SE ENLAZA LA BARRA DE NAVEGACION
        val bottomNavigationView : BottomNavigationView = context.findViewById(R.id.bottomNaViewBar)
        //SE MANDA A LLAMAR A LA CLASE QUE AYUDA A LA NAVEGACION
        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)

        val editTextSearch : EditText = findViewById(R.id.editTextSearch)
        val imageViewSearch : ImageView = findViewById(R.id.imageButtonSearch)
        val textViewError : TextView = findViewById(R.id.textViewError)

        imageViewSearch.setOnClickListener {
            var gridViewProducts = findViewById <GridView> (R.id.gridViewProductsSearch)
            var search : String = editTextSearch.text.toString()
            if(!search.isNullOrEmpty()){
                showProductsForSearch(search)
                if(listOfTennis == null){
                    textViewError.text = "No se han encontrado productos"
                    gridViewProducts.adapter = null
                    gridViewProducts.visibility = View.INVISIBLE
                }else{
                    gridViewProducts.visibility = View.VISIBLE
                    textViewError.text = ""
                    val adapter = AdapterTennis(this,listOfTennis!!)
                    gridViewProducts.adapter = adapter
                }
            }
        }
    }

    private fun showProductsForSearch(search: String){
        val productsString = SoapService().getProductsForSearch(search)
        if(productsString.isNotEmpty()){
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
        }else{
            listOfTennis = null
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
            "365215-06" -> return R.drawable.d1506
            "367232-02" -> return R.drawable.d202
            "818382-016" -> return R.drawable.d016
            "833671-201" -> return R.drawable.d201
            "839985-006" -> return R.drawable.d006
            "904260-602" -> return R.drawable.d0602
            "VN0A38EMU5D" -> return R.drawable.vn0
            else -> return  R.drawable.vn0001
        }

    }

}