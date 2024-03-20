package com.training.shopcartecom.model.callbacks

import com.training.shopcartecom.model.data.CategoryResponse

interface OperationalCallBack {
    fun onSuccess(response:Any)
    fun onFailure(message: String)
}