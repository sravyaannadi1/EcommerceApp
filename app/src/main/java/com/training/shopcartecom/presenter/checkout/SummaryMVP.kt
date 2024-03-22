package com.training.shopcartecom.presenter.checkout

import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.order.Addresse
import com.training.shopcartecom.model.data.order.OrderList
import com.training.shopcartecom.model.data.orderDetails.OrdersListResponse

interface SummaryMVP {
    interface ISummaryPresenter{

        fun getCartItems(): List<Cart>
        fun getSelectedAddress(): Addresse?
        fun getSelectedPayment():String?

        fun placeOrder()

        fun deleteCart()
    }

    interface SummaryView{
        fun setSuccess(orderId: String)
        fun setError()

    }
    interface IPaymentPresenter{
        fun savePaymentOption(paymentMode:String)
    }

    interface IOrderDetailsPresenter{
        fun getOrderDetails(orderId:String)
    }

    interface OrderDetailsView{

        fun setSuccess(orderDetailsResponse: OrderList)

        fun setError()
    }
    interface IOrdersListPresenter{

        fun getListOrders()
    }

    interface OrdersListView{

        fun setSuccess(ordersListResponse: OrdersListResponse)

        fun setError()
    }

}