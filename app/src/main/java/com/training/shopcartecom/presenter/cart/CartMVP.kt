package com.training.shopcartecom.presenter.cart

import com.training.shopcartecom.model.data.Cart

interface CartMVP {
    interface CartPresenter {
        fun getCartItems(): List<Cart>
    }
}