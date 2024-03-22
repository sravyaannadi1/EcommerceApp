package com.training.shopcartecom.presenter.checkout

import com.training.shopcartecom.model.VolleyHandler.CheckoutVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.order.Addresse
import com.training.shopcartecom.model.data.order.Checkout
import com.training.shopcartecom.model.data.order.UserProfileDetails
import com.training.shopcartecom.model.databasecart.CartDao

class SummaryPresenter (private val volleyHandler: CheckoutVolleyHandler, private val cartDao: CartDao, val  summaryView: SummaryMVP.SummaryView): SummaryMVP.ISummaryPresenter{
    override fun getCartItems(): List<Cart> {
        return cartDao.fetchProduct()
    }
    override fun getSelectedAddress(): Addresse? {
        return Checkout.address
    }

    override fun getSelectedPayment(): String? {
        return Checkout.paymentOption
    }
    override fun placeOrder() {
        UserProfileDetails.user?.let {
            volleyHandler.PostPlaceOrder(it.user_id,cartDao, object :OperationalCallBack{
                override fun onSuccess(response: Any?) {
                    (response as? String)?.let {
                        summaryView.setSuccess(it)
                    }
                }

                override fun onFailure(message: String) {
                    summaryView.setError()
                }

            })
        }
    }

    override fun deleteCart() {
        cartDao.deleteCart()
    }
}