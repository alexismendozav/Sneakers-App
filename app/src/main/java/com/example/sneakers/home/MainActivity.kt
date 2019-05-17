package com.example.sneakers.home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.GridView
import android.widget.ImageView
import com.example.sneakers.R
import com.example.sneakers.categories.CategoriesActivity
import com.example.sneakers.models.Tennis
import com.example.sneakers.util.AdapterTennis
import com.example.sneakers.util.BottomNavigationViewHelper
import com.example.sneakers.util.NetworkStatus
import com.example.sneakers.util.SoapService

class MainActivity : AppCompatActivity() {

    //App Context
    private val context = this

    //Help to know what activity is selected in the navigation bar
    private val numberOfActivity = 0

    //Other Variables
    private var listOfTennis : ArrayList <Tennis> ?= null
    private var listOfTennis2 : ArrayList <Tennis> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNaViewBar)
        val gridViewHome = findViewById <GridView> (R.id.gridViewHome)
        val imageView1 : ImageView = findViewById(R.id.imageView1)
        val imageView2 : ImageView = findViewById(R.id.imageView2)
        val imageView3 : ImageView = findViewById(R.id.imageView3)

        // Call the class that helps navigation
        BottomNavigationViewHelper().setupBottomNavigationView(numberOfActivity,context,bottomNavigationView)

        //Lists are initialized
        listOfTennis = ArrayList()
        listOfTennis2 = ArrayList()

        //Verify connection
        if(NetworkStatus().networkStatus(context)){
            //Call the method for fill in the lists
            showProductsForCategory("1",listOfTennis!!)
            //Call the adapter
            val adapter = AdapterTennis(this,listOfTennis!!)

            imageView1.setImageResource(R.drawable.home1)
            gridViewHome.adapter = adapter
            imageView2.setImageResource(R.drawable.home3)
            imageView3.setImageResource(R.drawable.home2)

        }else{
            val imageViewErrorNetwork : ImageView = findViewById(R.id.imageViewErrorNetwork)
            imageViewErrorNetwork.setImageResource(R.drawable.error)
        }

        imageView1.setOnClickListener{
            val intent= Intent(this,CategoriesActivity::class.java)
            startActivity(intent)
        }

        imageView2.setOnClickListener{
            val intent= Intent(this,CategoriesActivity::class.java)
            startActivity(intent)
        }

        imageView3.setOnClickListener{
            val intent= Intent(this,CategoriesActivity::class.java)
            startActivity(intent)
        }
    }

    //Call the SOAP method and fill in the list
    private fun showProductsForCategory(category: String,list : ArrayList<Tennis>){
        //Call the SOAP method
        val productsString = SoapService().getProductsForCategory(category)

        //Divide the string
        val products = productsString.split("\n")

        //Tennis objects are created
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
