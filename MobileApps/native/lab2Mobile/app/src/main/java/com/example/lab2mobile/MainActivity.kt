package com.example.lab2mobile

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2mobile.model.Product

var prod1 =  Product(5, "vodka", "3 eur","10")
var prod2 = Product(5, "pizza", "3 eur","4")
var prod3 =  Product(5, "whisky", "3 eur","3")
var prod4 =  Product(5, "cereal", "3 eur","6")
var prod5 =  Product(5, "hotdog", "3 eur","1")
var prod6 = Product(5, "pampers", "3 eur","9")
var prod7 = Product(6, "water", "3 eur","10")
var prod8 = Product(7,"juice", "3 eur","3")
var prod9 = Product(8, "star wars figurine", "3 eur","2")
var prod10 = Product(9, "bread", "3 eur","3")

var products: MutableList<Product> = mutableListOf(prod1,prod2,prod3,prod4,prod5,prod6,prod7,prod8,prod9,prod10)

class MainActivity : AppCompatActivity() {

    var product_names: Array<String> = emptyArray()
    var product_prices: Array<String> = emptyArray()
    var product_quantitys: Array<String> = emptyArray()
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        var button: Button = findViewById(R.id.button)
        button.setOnClickListener{
            var intent: Intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, 555);
        }
        var adapter: ProductAdapter = ProductAdapter(products,this)
        recyclerView.adapter=adapter
        var linearManager: LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearManager

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(555 == requestCode && resultCode == Activity.RESULT_OK) {
            val newProduct: Product = data?.getSerializableExtra("new_product") as Product
            products.add(newProduct)
        }
        else if(1111 == requestCode && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra("new_name").toString()
            val price = data?.getStringExtra("new_price").toString()
            val quantity = data?.getStringExtra("new_quantity").toString()

            val position: Int = data?.getIntExtra("position",1)!!

            var oldProduct = products.get(position)
            var newProduct = Product(oldProduct.prodId, name, price ,quantity)
            products[position] = newProduct

        }
        recyclerView.adapter?.notifyDataSetChanged()

    }
}