package com.training.shopcartecom.model.VolleyHandler

import android.annotation.SuppressLint
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.Constants
import com.training.shopcartecom.model.data.Constants.ADDRESS_URL
import com.training.shopcartecom.model.data.Constants.PLACE_ORDER_URL
import com.training.shopcartecom.model.data.order.AddressIn
import com.training.shopcartecom.model.data.order.AddressList
import com.training.shopcartecom.model.data.order.Checkout
import com.training.shopcartecom.model.data.order.OrderList
import com.training.shopcartecom.model.data.orderDetails.OrdersListResponse
import com.training.shopcartecom.model.databasecart.CartDao
import org.json.JSONArray
import org.json.JSONObject

class CheckoutVolleyHandler(val context: Context) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        /*
        @Volatile: meaning that writes to this field are immediately made visible to other threads.
         */
        @Volatile
        private var instance: CheckoutVolleyHandler? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: CheckoutVolleyHandler(context)
        }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAddressDetails(userId: String, responseCallback: OperationalCallBack) {
        val url = "$ADDRESS_URL$userId"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object : TypeToken<AddressList>() {}
                val response = Gson().fromJson(it, typeToken)
                responseCallback.onSuccess(response)
            }, {
                responseCallback.onFailure("error")
            }
        )
        requestQueue.add(stringRequest)
    }

    fun PostAddressDetails(postAddressResponse: AddressIn, responseCallback: OperationalCallBack) {
        val params = HashMap<String, String>()
        params.put("user_id", postAddressResponse.user_id.toString())
        params.put("title", postAddressResponse.title)
        params.put("address", postAddressResponse.address)

        val request = JSONObject(Gson().toJson(postAddressResponse))

        val stringRequest = JsonObjectRequest(
            Request.Method.POST,
            Constants.ADD_ADDRESS_URL,
            request,
            {
                responseCallback.onSuccess(null)
            },
            {
                responseCallback.onFailure("error")
            }
        )
        requestQueue.add(stringRequest)
    }

    fun PostPlaceOrder(userid: String, cartDao: CartDao, responseCallback: OperationalCallBack) {

        val deliveryObject = JSONObject().apply {
            put("title", Checkout.address?.title)
            put("address", Checkout.address?.address)
        }

        var totalAmount = 0F
        val cartItemsArray = JSONArray().apply {
            cartDao.fetchProduct().forEachIndexed { index, cartItem ->
                val cartItemObject = JSONObject().apply {
                    put("product_id", cartItem.id.toInt())
                    put("quantity", cartItem.quantity.toLong())
                    put("unit_price", cartItem.unitPrice.toFloat())
                }
                totalAmount += cartItem.price.toFloat()
                put(index, cartItemObject)

            }
        }

        val jsonRequest = JSONObject()
        jsonRequest.apply {
            put("user_id", userid.toInt())
            put("items", cartItemsArray)
            put("delivery_address", deliveryObject)
            put("bill_amount", totalAmount)
            put("payment_method", "COD")
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            PLACE_ORDER_URL,
            jsonRequest,
            {
                responseCallback.onSuccess(it.getString("order_id"))
            },
            {
                responseCallback.onFailure("error")
            }
        )
        requestQueue.add(request)
    }

    fun getOrderDetails(orderId: String, responseCallback: OperationalCallBack) {

        val queryParams = "order_id=${orderId}"
        val url = " ${Constants.ORDER_DETAILS_URL}${queryParams}"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object : TypeToken<OrderList>() {}
                val response = Gson().fromJson(it, typeToken)
                responseCallback.onSuccess(response)
            }, {
                responseCallback.onFailure("error")
            }
        )
        requestQueue.add(stringRequest)
    }
    fun listOfOrders(userId:String,responseCallback: OperationalCallBack) {
        val stringRequest = StringRequest(
            Request.Method.GET,
            Constants.LIST_ORDERS_URL + userId,
            {
                val typeToken = object : TypeToken<OrdersListResponse>() {}
                val response = Gson().fromJson(it, typeToken)
                responseCallback.onSuccess(response)
            }, {
                responseCallback.onFailure("error")
            }
        )
        requestQueue.add(stringRequest)
    }

}