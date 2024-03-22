package com.training.shopcartecom.presenter.cart

import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.databasecart.CartDao

class CartPresenter(private val cartDao: CartDao): CartMVP.CartPresenter {
    override fun getCartItems(): List<Cart> {
        return cartDao.fetchProduct()
    }
}