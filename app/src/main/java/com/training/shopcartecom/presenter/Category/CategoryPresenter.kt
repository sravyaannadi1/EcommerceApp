package com.training.shopcartecom.presenter.Category

import com.training.shopcartecom.model.VolleyHandler.CategoryVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.CategoryResponse
import com.training.shopcartecom.model.data.Constants
import com.training.shopcartecom.view.category.CategoryData

class CategoryPresenter(
    private val volleyHandler: CategoryVolleyHandler,
    private val categoryView: CategoryMVP.CategoryView
): CategoryMVP.CategoryPresenter {
    override fun fetchCategoryData() {
        volleyHandler.makeApiCallCategory(object : OperationalCallBack{
            override fun onSuccess(response: Any) {
                response as CategoryResponse
                categoryView.setResultCategory(
                    response.categories.map {
                        CategoryData(
                            category_name = it.category_name,
                            category_image_url = Constants.BASE_URL_IMAGE+it.category_image_url,
                            category_id = it.category_id
                        )
                    }
                )
            }

            override fun onFailure(message: String) {
                categoryView.onError(message)
            }
        })
    }
}