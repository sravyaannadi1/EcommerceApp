package com.training.shopcartecom.presenter.User

import com.training.shopcartecom.model.data.LoginData
import com.training.shopcartecom.model.data.UserData

interface MVPLogin {
    interface LoginPresenter{
        fun sendLoginData(data: LoginData)
    }
    interface LoginView{
        fun setResultLogin(status:Int, message:String)
    }
}