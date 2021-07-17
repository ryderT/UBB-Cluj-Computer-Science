package com.example.lab2mobile

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.lab2mobile.model.Product

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        var addButton: Button = findViewById(R.id.add_button)
        var editName: EditText = findViewById(R.id.edit_text_name2)
        var editPrice: EditText = findViewById(R.id.edit_text_price2)
        var editQuantity: EditText = findViewById(R.id.edit_text_quantity)
        addButton.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
                .setTitle("Are you sure you want to add?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add product", null)
                .show()
            dialog.getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener {
                    dialog.dismiss()
                    var name: String = editName.text.toString()
                    var price = editPrice.text.toString()
                    var quantity = editQuantity.text.toString()
                    var product: Product = Product(0, name, price, quantity)
                    Log.d("add product", product.name)

                    // return to parent activity
                    val intent = Intent()
                    intent.putExtra("new_product", product)
                    setResult(Activity.RESULT_OK, intent)
                    finish()

                }
        }

    }
}