package com.training.shopcartecom.model.VolleyHandler

import android.content.Context
import android.util.Log
import com.android.volley.Request.Method
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.CategoryResponse
import com.training.shopcartecom.model.data.Constants.BASE_URL_CATEGORY

class CategoryVolleyHandler(private val context: Context) {
    private lateinit var response: CategoryResponse
    private val requestQueue by lazy { Volley.newRequestQueue(context) }
    fun makeApiCallCategory(callBack: OperationalCallBack){
        val stringRequest=StringRequest(Method.GET,BASE_URL_CATEGORY,{
         val typeToken=object : TypeToken<CategoryResponse>() {}
            val response=Gson().fromJson(it, typeToken)
            Log.i("tag","${response.status}")
            callBack.onSuccess(response) },{
                Log.i("error_tag","${it.toString()}")
            callBack.onFailure("Error")
        })
        requestQueue.add(stringRequest)
    }

}