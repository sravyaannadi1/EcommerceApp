package com.training.shopcartecom.model.data.productdetails

data class ProductDescriptionResponse(
    val message: String,
    val product: Product,
    val status: Int
)