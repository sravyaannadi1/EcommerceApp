package com.training.shopcartecom.presenter.User

import com.training.shopcartecom.model.data.UserData

interface RegisterMVP {
    interface RegisterPresenter{
        fun sendRegisterData(data: UserData)
    }
    interface RegisterView{
        fun setResultRegister(status:Int, message:String)
    }
}