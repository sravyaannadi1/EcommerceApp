package com.training.shopcartecom.presenter.subcategory

import com.training.shopcartecom.model.VolleyHandler.SubCategoryVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.Constants
import com.training.shopcartecom.model.data.Subcategory
import com.training.shopcartecom.model.data.SubcategoryResponse
import com.training.shopcartecom.view.subCategory.SubCategoryData

class SubCategoryPresenter (
            private val volleyHandler: SubCategoryVolleyHandler,
            private val subCategoryView:SubCategoryMVP.SubCategoryView
            ):SubCategoryMVP.SubCategoryPresenter {
    override fun fetchSubCategoryData(category_id:String) {
        volleyHandler.makeApiCallSubCategory(category_id,object : OperationalCallBack{
            override fun onSuccess(response: Any) {
                response as SubcategoryResponse
                subCategoryView.setResultSubCategory(
                    response.subcategories.map {
                        SubCategoryData(
                            subcategory_id = it.subcategory_id,
                            subcategory_name = it.subcategory_name
                        )
                    }
                )
            }

            override fun onFailure(message: String) {
                subCategoryView.onError(message)
            }

        })
    }
}