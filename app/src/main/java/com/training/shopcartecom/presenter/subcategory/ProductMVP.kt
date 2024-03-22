package com.training.shopcartecom.presenter.subcategory

import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.ProductResponse
import com.training.shopcartecom.view.subCategory.ProductData
import com.training.shopcartecom.view.subCategory.SubCategoryData

interface ProductMVP {
    interface ProductPresenter{
        fun fetchProductData(subcategory_id: String)
        fun fetchCart():List<Cart>

    }
    interface ProductView{
        fun setResultProduct(productResponse: ProductResponse)
        fun onError(message: String)
    }
}