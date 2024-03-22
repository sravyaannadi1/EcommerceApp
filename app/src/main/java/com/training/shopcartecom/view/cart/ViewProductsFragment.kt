package com.training.shopcartecom.view.cart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentViewProductsBinding
import com.training.shopcartecom.model.databasecart.AppDatabase
import com.training.shopcartecom.model.databasecart.CartDao
import com.training.shopcartecom.presenter.cart.CartPresenter


class ViewProductsFragment : Fragment() {
    private lateinit var binding:FragmentViewProductsBinding
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var presenter: CartPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentViewProductsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDB()
        binding.btnNext.setOnClickListener {
            (parentFragment as CheckoutFragment).moveToNext(1)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initDB(){
        appDatabase = AppDatabase.getAppDatabase(activity?.applicationContext!!)
        cartDao = appDatabase.getCartDao()

        presenter = (CartPresenter(cartDao))

        val cartItems = presenter.getCartItems()

        var totalPrice = 0F
        cartItems.forEach {
            totalPrice += it.price.toFloat()
        }
        binding.tvTotalPrice.text = "$ $totalPrice"
        val adapter = CartItemsAdapter(cartItems)
        binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCartItems.adapter = adapter
    }

}