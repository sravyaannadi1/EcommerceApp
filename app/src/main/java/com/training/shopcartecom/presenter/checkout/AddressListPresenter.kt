package com.training.shopcartecom.presenter.checkout

import com.training.shopcartecom.model.VolleyHandler.CheckoutVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.order.AddressIn
import com.training.shopcartecom.model.data.order.AddressList
import com.training.shopcartecom.model.data.order.Addresse
import com.training.shopcartecom.model.data.order.Checkout
import com.training.shopcartecom.model.data.order.UserProfileDetails

class AddressListPresenter (private val volleyHandler: CheckoutVolleyHandler, val deliveryAddressView: AddressMVP.DeliveryAddressView)
    :AddressMVP.DeliveryAddressPresenter{
    override fun getAddressDetails() {

        UserProfileDetails.user?.user_id?.let {
            volleyHandler.getAddressDetails(it, object : OperationalCallBack {
                override fun onSuccess(response: Any?) {
                    (response as? AddressList)?.let {
                        deliveryAddressView.setGetAddressSuccess(it)
                    }
                }

                override fun onFailure(message: String) {
                    deliveryAddressView.setError()
                }


            })
        }
    }
    override fun saveSelectedAddress(address: Addresse) {
        Checkout.address = address
    }
    override fun addNewAddress(postAddressRequest: AddressIn) {
        volleyHandler.PostAddressDetails(postAddressRequest, object: OperationalCallBack{
            override fun onSuccess(response: Any?) {
                deliveryAddressView.setAddAdressSuccess()
            }
            override fun onFailure(message: String) {
                deliveryAddressView.setError()
            }
        })
    }
}