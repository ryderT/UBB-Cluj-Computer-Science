package com.example.lab2mobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2mobile.model.Product


class ProductAdapter(var products: MutableList<Product>,var context: Context):
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(
        itemView: View,
        context: Context,
        products: MutableList<Product>
    ) : RecyclerView.ViewHolder(itemView) {
        var context: Context = context
        var products: MutableList<Product> = products

        var nameView: TextView = itemView.findViewById(R.id.text_name)
        var priceView: TextView = itemView.findViewById(R.id.text_price)
        var quantityView: TextView = itemView.findViewById(R.id.text_quantity)

        var imgDeleteView: ImageView = itemView.findViewById(R.id.img_delete)
        var imgEditView: ImageView = itemView.findViewById(R.id.img_edit)

        fun bind(
            product: Product,
            positionAdapter: Int,
            productAdapter: ProductAdapter
        ){
            nameView.setText(product.name)
            priceView.setText(product.price)
            quantityView.setText(product.quantity)
            imgDeleteView.setOnClickListener{
                Log.d("delete clicked", "element: " + nameView.text + ",position: " + this.adapterPosition)
                products.removeAt(this.adapterPosition)
                productAdapter.notifyDataSetChanged()
            }
            imgEditView.setOnClickListener{
                Log.d("edit clicked", "element: " + nameView.text)
                var intent: Intent = Intent(context, EditActivity::class.java)
                intent.putExtra("product", product)
                intent.putExtra("position", positionAdapter)
                (context as Activity).startActivityForResult(intent, 1111)


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        var view: View = inflater.inflate(R.layout.item_layout, parent, false)
        return ProductViewHolder(view, context, products)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = products[position]
        holder.bind(currentItem, position, this)

    }
}