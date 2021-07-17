package com.example.lab2mobile.model

import java.io.Serializable

class Product(var prodId: Long,
              var name: String,
              var price: String,
              var quantity: String): Serializable{}