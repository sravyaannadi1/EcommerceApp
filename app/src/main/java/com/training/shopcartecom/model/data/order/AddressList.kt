package com.training.shopcartecom.model.data.order

data class AddressList(
    val addresses: List<Addresse>,
    val message: String,
    val status: Int
)