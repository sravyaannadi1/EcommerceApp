package com.training.shopcartecom.model.data

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)