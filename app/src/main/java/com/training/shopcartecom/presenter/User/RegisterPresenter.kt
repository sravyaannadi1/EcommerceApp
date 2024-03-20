package com.training.shopcartecom.presenter.User

import com.training.shopcartecom.model.callbacks.RegisterOperationalCallBack
import com.training.shopcartecom.model.data.UserData
import com.training.shopcartecom.model.VolleyHandler.UserVolleyHandler

class RegisterPresenter(private val volleyHandler: UserVolleyHandler, private val registerView: RegisterMVP.RegisterView):RegisterMVP.RegisterPresenter {
    override fun sendRegisterData(data: UserData) {
        volleyHandler.postRegistration(data.email_id,data.full_name,data.mobile_no,data.password,object :
            RegisterOperationalCallBack {
            override fun onSuccessRegistration(status: Int, response: String){
                registerView.setResultRegister(0, response)
            }
            override fun onFailureRegistration(status: Int, error: String) {
                registerView.setResultRegister(1,error)
            }}
        )
    }
}