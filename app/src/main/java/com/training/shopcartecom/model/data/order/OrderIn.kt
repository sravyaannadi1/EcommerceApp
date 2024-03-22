package com.training.shopcartecom.model.data.order

data class OrderIn(
    val bill_amount: Int,
    val delivery_address: DeliveryAddress,
    val items: List<Item>,
    val payment_method: String,
    val user_id: Int
)