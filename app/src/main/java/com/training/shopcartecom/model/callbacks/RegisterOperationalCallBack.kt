package com.training.shopcartecom.model.callbacks

interface RegisterOperationalCallBack {
    fun onSuccessRegistration(status: Int, response: String)
    fun onFailureRegistration(status: Int, error: String)

}