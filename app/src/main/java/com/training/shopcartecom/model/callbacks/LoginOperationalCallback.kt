package com.training.shopcartecom.model.callbacks

interface LoginOperationalCallback {
    fun onSuccessLogin(status: Int,message:String)
    fun onFailureLogin(status: Int, error: String)
}