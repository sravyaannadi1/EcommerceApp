package com.training.shopcartecom.presenter.Category

import com.training.shopcartecom.view.category.CategoryData

interface CategoryMVP {
    interface CategoryPresenter{
        fun fetchCategoryData()
    }
    interface CategoryView{
        fun setResultCategory(categoryList: List<CategoryData>)
        fun onError(message: String)
    }
}