package com.training.shopcartecom.presenter.checkout

import com.training.shopcartecom.model.data.order.AddressIn
import com.training.shopcartecom.model.data.order.AddressList
import com.training.shopcartecom.model.data.order.Addresse

interface AddressMVP {
    interface DeliveryAddressPresenter {
        fun getAddressDetails()

        fun saveSelectedAddress(address: Addresse)
        fun addNewAddress(postAddressRequest: AddressIn)
    }
    interface DeliveryAddressView {
        fun setError()

        fun setGetAddressSuccess(userAddressResponse: AddressList)

        fun setAddAdressSuccess()
    }
}