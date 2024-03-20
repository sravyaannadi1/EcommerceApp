package com.training.shopcartecom.model.VolleyHandler

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.training.shopcartecom.model.callbacks.LoginOperationalCallback
import com.training.shopcartecom.model.data.Constants.BASE_URL_REGISTRATION
import com.training.shopcartecom.model.callbacks.RegisterOperationalCallBack
import com.training.shopcartecom.model.data.Constants.BASE_URL_LOGIN

class UserVolleyHandler(private val context: Context) {

    fun postRegistration(email_id: String,full_name: String,mobile_no: String,password: String,callBack: RegisterOperationalCallBack) {
        val requestQueue = Volley.newRequestQueue(context)
        val params = HashMap<String, String>()
        params["email_id"] = email_id
        params["full_name"] = full_name
        params["mobile_no"] = mobile_no
        params["password"] = password
        val stringRequest = object : StringRequest(
            Method.POST, BASE_URL_REGISTRATION, { response ->
             Log.i("tag","success")
callBack.onSuccessRegistration(0,response)
            }, {
                error->
                Log.i("tag","error")
callBack.onFailureRegistration(1,error.message?:"unknown error")
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        Log.i("tag","${stringRequest.url} + ${stringRequest.headers}, ${stringRequest.body} ")
                requestQueue.add(stringRequest)
    }
    fun postLogin(email_id: String,password: String,callBack: LoginOperationalCallback) {
        val requestQueue = Volley.newRequestQueue(context)
        val params = HashMap<String, String>()
        params["email_id"] = email_id
        params["password"] = password
        val stringRequest = object : StringRequest(
            Method.POST, BASE_URL_LOGIN, { response ->
                callBack.onSuccessLogin(0,response)

            }, {
                    error->
                callBack.onFailureLogin(1,error.message?:"Failed to login. Please check your email id or password")
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        requestQueue.add(stringRequest)
    }


}
