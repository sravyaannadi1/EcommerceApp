package com.training.shopcartecom.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey
    val id: String,
    val unitPrice: String,
    var quantity: Int = 0,
    val price:String = (unitPrice.toFloat()*quantity).toString(),
    val product_name: String,
    val description: String,
    val product_image_url: String,
)