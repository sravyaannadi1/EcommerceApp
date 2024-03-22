package com.training.shopcartecom.presenter.subcategory

import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.productdetails.ProductDescriptionResponse
import com.training.shopcartecom.view.subCategory.ProductData

interface ProductDetailsMVP {
    interface ProductDetailsPresenter{
        fun fetchProductDetails(product_id: String)
        fun addCart(cart: Cart)
        fun deleteItem(cart: Cart)
        fun fetchCartwithId(prodcutId: String): Cart?
    }
    interface ProductDeatailsView{
        fun setResultProduct(productDescriptionResponse: ProductDescriptionResponse)
        fun onError(message: String)
    }
}