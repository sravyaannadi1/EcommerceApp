package com.training.shopcartecom.presenter.subcategory

import com.training.shopcartecom.model.data.Subcategory
import com.training.shopcartecom.view.subCategory.SubCategoryData

interface SubCategoryMVP {
    interface SubCategoryPresenter{
        fun fetchSubCategoryData(category_id: String)
    }
    interface SubCategoryView{
        fun setResultSubCategory(subcategoryList: List<SubCategoryData>)
        fun onError(message: String)
    }
}