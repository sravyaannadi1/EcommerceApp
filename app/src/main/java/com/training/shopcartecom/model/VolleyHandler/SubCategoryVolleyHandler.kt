package com.training.shopcartecom.model.VolleyHandler

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.Constants
import com.training.shopcartecom.model.data.ProductResponse
import com.training.shopcartecom.model.data.SubcategoryResponse

class SubCategoryVolleyHandler(private val context: Context) {
    private lateinit var categoryResponse: SubcategoryResponse
    private val requestQueue by lazy { Volley.newRequestQueue(context) }
    fun makeApiCallSubCategory(categoryId: String, callBack: OperationalCallBack){
        val url=Constants.BASE_URL_SUBCATEGORY+categoryId
        val stringRequest= StringRequest(Request.Method.GET, url,{
            val typeToken=object : TypeToken<SubcategoryResponse>() {}
            val response= Gson().fromJson(it, typeToken)
            callBack.onSuccess(response) },{
            Log.i("error_tag","${it.toString()}")
            callBack.onFailure("Error")
        })
        requestQueue.add(stringRequest)
    }

    fun makeApiCallProduct(subcategory_id: String, callBack: OperationalCallBack){
        val url=Constants.BASE_URL_PRODUCT+subcategory_id
        val stringRequest= StringRequest(Request.Method.GET,url,{
        val typeToken=object :TypeToken<ProductResponse>(){}
            val response=Gson().fromJson(it,typeToken)
            callBack.onSuccess(response)
        },{
            Log.i("error_tag","${it.toString()}")
            callBack.onFailure("Error")
        })
        requestQueue.add(stringRequest)
    }

}