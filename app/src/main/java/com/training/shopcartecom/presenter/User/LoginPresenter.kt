package com.training.shopcartecom.presenter.User

import com.training.shopcartecom.model.VolleyHandler.UserVolleyHandler
import com.training.shopcartecom.model.callbacks.LoginOperationalCallback
import com.training.shopcartecom.model.callbacks.RegisterOperationalCallBack
import com.training.shopcartecom.model.data.LoginData
import com.training.shopcartecom.model.data.UserData

class LoginPresenter(private val volleyHandler: UserVolleyHandler, private val loginView: MVPLogin.LoginView):MVPLogin.LoginPresenter {
    override fun sendLoginData(data: LoginData) {
        volleyHandler.postLogin(data.email_id,data.password,object: LoginOperationalCallback{
            override fun onSuccessLogin(status: Int, message: String) {
                loginView.setResultLogin(0,message)
            }

            override fun onFailureLogin(status: Int, error: String) {
                loginView.setResultLogin(1,error)
            }
        })
    }
}