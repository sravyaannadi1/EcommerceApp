package com.training.shopcartecom.presenter.subcategory

import com.training.shopcartecom.view.subCategory.ProductData
import com.training.shopcartecom.view.subCategory.SubCategoryData

interface ProductMVP {
    interface ProductPresenter{
        fun fetchProductData(product_id: String)
    }
    interface ProductView{
        fun setResultProduct(productList: List<ProductData>)
        fun onError(message: String)
    }
}