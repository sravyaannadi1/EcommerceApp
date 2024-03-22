package com.training.shopcartecom.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.shopcartecom.databinding.FragmentDeliveryBinding
import com.training.shopcartecom.model.VolleyHandler.CheckoutVolleyHandler
import com.training.shopcartecom.model.data.order.AddressIn
import com.training.shopcartecom.model.data.order.AddressList
import com.training.shopcartecom.model.data.order.Addresse
import com.training.shopcartecom.model.data.order.UserProfileDetails
import com.training.shopcartecom.presenter.checkout.AddressListPresenter
import com.training.shopcartecom.presenter.checkout.AddressMVP


class DeliveryFragment : Fragment(), AddAddressClickListener, IsSelectedListener {

    private lateinit var binding: FragmentDeliveryBinding
    private lateinit var presenter: AddressListPresenter
    private lateinit var adapter : DeliveryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AddressListPresenter(CheckoutVolleyHandler.getInstance(requireContext()),object:
            AddressMVP.DeliveryAddressView{
            override fun setError() {
            }
            override fun setGetAddressSuccess(userAddressResponse: AddressList) {
                adapter = DeliveryAdapter(userAddressResponse.addresses, this@DeliveryFragment)
                context?.let {
                    binding.rvAddress.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvAddress.adapter = adapter
                }
            }
            override fun setAddAdressSuccess() {
                presenter.getAddressDetails()
            }

        })
        binding.btnAddAddress.setOnClickListener {

            val bottomSheet = AddressFragment(this)
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheet.show(it1,"Add Address")
            }
        }
        presenter.getAddressDetails()
        binding.btnNext.setOnClickListener {
            (parentFragment as CheckoutFragment).moveToNext(2)
        }
    }
    override fun onAddClicked(title: String, address: String) {
        presenter.addNewAddress(AddressIn(address, title, UserProfileDetails.user?.user_id?.toInt()?:0))
    }

    override fun saveAddress(address: Addresse) {
        presenter.saveSelectedAddress(address)
    }
}