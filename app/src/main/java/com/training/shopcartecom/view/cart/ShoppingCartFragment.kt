package com.training.shopcartecom.view.cart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentShoppingCartBinding
import com.training.shopcartecom.model.databasecart.AppDatabase
import com.training.shopcartecom.model.databasecart.CartDao
import com.training.shopcartecom.presenter.cart.CartMVP
import com.training.shopcartecom.presenter.cart.CartPresenter
import com.training.shopcartecom.view.subCategory.ItemClickListener
import com.training.shopcartecom.view.subCategory.ProductDetailsFragment


class ShoppingCartFragment : Fragment(),ItemClickListener {
    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var productDetailsFragment: ProductDetailsFragment
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentShoppingCartBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDB()
        val presenter=CartPresenter(cartDao)
        val cart=presenter.getCartItems()
        var cartTotal= 0F
      cart.forEach{
          cartTotal+=it.price.toFloat()
      }
        binding.totalPrice.text="$cartTotal"
        val adapter=CartAdapter(cart,this)
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter
        binding.checkout.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.containerHome,CheckoutFragment()).addToBackStack(null).commit()
        }

    }

    private fun initDB() {
        appDatabase=AppDatabase.getAppDatabase(activity?.applicationContext!!)
        cartDao=appDatabase.getCartDao()
    }

    override fun isSelected(id: String) {
        productDetailsFragment=ProductDetailsFragment()
        val bundle=Bundle()
        bundle.putString("product_id",id)
        productDetailsFragment.arguments=bundle
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.containerHome,productDetailsFragment).addToBackStack(null).commit()
    }

}