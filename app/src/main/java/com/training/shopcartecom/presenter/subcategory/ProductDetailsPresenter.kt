package com.training.shopcartecom.presenter.subcategory

import com.training.shopcartecom.model.VolleyHandler.SubCategoryVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.productdetails.ProductDescriptionResponse
import com.training.shopcartecom.model.databasecart.CartDao

class ProductDetailsPresenter(private val volleyHandler: SubCategoryVolleyHandler,private val cartDao: CartDao,
    val productDeatailsView: ProductDetailsMVP.ProductDeatailsView):ProductDetailsMVP.ProductDetailsPresenter {
    override fun fetchProductDetails(product_id: String) {
        volleyHandler.makeApiCallProductDetails(product_id, object : OperationalCallBack{
            override fun onSuccess(response: Any?) {
                response as ProductDescriptionResponse
                productDeatailsView.setResultProduct(response)
            }

            override fun onFailure(message: String) {
                productDeatailsView.onError(message)
            }
        })
    }

    override fun addCart(cart: Cart) {
        cartDao.insertProduct(cart)
    }

    override fun deleteItem(cart: Cart) {
        cartDao.deleteProduct(cart)
    }

    override fun fetchCartwithId(prodcutId: String): Cart? {
        return cartDao.fetchProductWithId(prodcutId)
    }

}

