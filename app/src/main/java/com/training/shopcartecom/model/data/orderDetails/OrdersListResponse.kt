package com.training.shopcartecom.model.data.orderDetails

data class OrdersListResponse(    val message: String,
                                  val orders: List<Order>,
                                  val status: Int)
