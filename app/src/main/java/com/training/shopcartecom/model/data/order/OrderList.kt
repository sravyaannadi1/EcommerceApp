package com.training.shopcartecom.model.data.order

data class OrderList(
    val message: String,
    val orders: List<Order>,
    val status: Int
)