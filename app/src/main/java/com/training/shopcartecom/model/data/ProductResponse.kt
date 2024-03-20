package com.training.shopcartecom.model.data

data class ProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)