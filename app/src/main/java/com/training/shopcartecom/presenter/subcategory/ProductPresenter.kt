package com.training.shopcartecom.presenter.subcategory

import com.training.shopcartecom.model.VolleyHandler.SubCategoryVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.Constants
import com.training.shopcartecom.model.data.Product
import com.training.shopcartecom.model.data.ProductResponse
import com.training.shopcartecom.model.data.SubcategoryResponse
import com.training.shopcartecom.model.databasecart.CartDao
import com.training.shopcartecom.view.subCategory.ProductData
import com.training.shopcartecom.view.subCategory.SubCategoryData

class ProductPresenter(private val volleyHandler: SubCategoryVolleyHandler, val cartDao: CartDao, val productsView: ProductMVP.ProductView)
    : ProductMVP.ProductPresenter{
    override fun fetchProductData(subCatId: String) {
        volleyHandler.makeApiCallProduct(subCatId, object:OperationalCallBack{

            override fun onSuccess(response: Any?) {
                (response as? ProductResponse)?.let {
                    productsView.setResultProduct(it)
                }
            }

            override fun onFailure(message: String) {
                productsView.onError(message)
            }

        })
    }

    override fun fetchCart(): List<Cart> {
        return cartDao.fetchProduct()
    }




}