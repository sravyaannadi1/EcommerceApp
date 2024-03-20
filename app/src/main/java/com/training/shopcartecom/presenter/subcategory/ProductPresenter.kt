package com.training.shopcartecom.presenter.subcategory

import com.training.shopcartecom.model.VolleyHandler.SubCategoryVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.Constants
import com.training.shopcartecom.model.data.ProductResponse
import com.training.shopcartecom.model.data.SubcategoryResponse
import com.training.shopcartecom.view.subCategory.ProductData
import com.training.shopcartecom.view.subCategory.SubCategoryData

class ProductPresenter
    (
    private val volleyHandler: SubCategoryVolleyHandler,
    private val productView:ProductMVP.ProductView
):ProductMVP.ProductPresenter {

    override fun fetchProductData(product_id:String) {
        volleyHandler.makeApiCallProduct(product_id,object : OperationalCallBack {
            override fun onSuccess(response: Any) {
                response as ProductResponse
                productView.setResultProduct(
                    response.products.map {
                        ProductData(
                            product_id=it.product_id,
                            product_name = it.product_name,
                            description = it.description,
                            product_image_url = Constants.BASE_URL_IMAGE+it.product_image_url,
                            price = it.price

                        )
                    }
                )

            }

            override fun onFailure(message: String) {
                productView.onError(message)
            }

        })
    }
}