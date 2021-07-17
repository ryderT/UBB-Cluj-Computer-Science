package com.example.lab2mobile

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.lab2mobile.model.Product


class EditActivity : AppCompatActivity() {
    lateinit var editTextName: EditText
    lateinit var editTextPrice: EditText
    lateinit var editTextQuantity: EditText
    lateinit var product: Product
    lateinit var updateButton: Button
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        editTextName = findViewById(R.id.edit_text_name)
        editTextPrice = findViewById(R.id.edit_text_price)
        editTextQuantity=findViewById(R.id.edit_text_quantity)
        updateButton=findViewById(R.id.update_button)

        product = intent.getSerializableExtra("product") as Product
        position = intent.getIntExtra("position",1)
        editTextName.setText(product.name)
        editTextPrice.setText(product.price)
        editTextQuantity.setText(product.quantity)

        var buttonUpdate: Button = findViewById(R.id.update_button)
        buttonUpdate.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
                .setTitle("Are you sure you want to update?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Update product", null)
                .show()

            dialog.getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener {
                    dialog.dismiss()
                    product.name = editTextName.text.toString()
                    product.price = editTextPrice.text.toString()
                    product.quantity=editTextQuantity.text.toString()

                    // return to parent activity
                    val intent = Intent()
                    intent.putExtra("new_name", product.name)
                    intent.putExtra("new_price", product.price)
                    intent.putExtra("new_quantity",product.quantity)
                    intent.putExtra("position", position)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }

        }
    }
}