package com.training.shopcartecom.presenter.checkout

import com.training.shopcartecom.model.VolleyHandler.CheckoutVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.model.data.order.UserProfileDetails
import com.training.shopcartecom.model.data.orderDetails.OrdersListResponse

class OrderListPresenter (private val volleyHandler: CheckoutVolleyHandler, val ordersListView: SummaryMVP.OrdersListView):
    SummaryMVP.IOrdersListPresenter{
    override fun getListOrders() {
        UserProfileDetails.user?.user_id?.let {
            volleyHandler.listOfOrders(it, object:OperationalCallBack{
                override fun onSuccess(response: Any?) {

                    (response as? OrdersListResponse)?.let{

                        ordersListView.setSuccess(it)
                    }
                }
                override fun onFailure(message: String) {
                    ordersListView.setError()
                }

            })
        }
    }
}