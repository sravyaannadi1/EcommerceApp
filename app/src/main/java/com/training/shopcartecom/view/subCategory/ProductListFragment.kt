package com.training.shopcartecom.view.subCategory

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentProductListBinding
import com.training.shopcartecom.model.VolleyHandler.SubCategoryVolleyHandler
import com.training.shopcartecom.model.data.Product
import com.training.shopcartecom.model.data.ProductResponse
import com.training.shopcartecom.model.databasecart.AppDatabase
import com.training.shopcartecom.model.databasecart.CartDao
import com.training.shopcartecom.presenter.subcategory.ProductMVP
import com.training.shopcartecom.presenter.subcategory.ProductPresenter
import com.training.shopcartecom.presenter.subcategory.SubCategoryMVP


class ProductListFragment : Fragment(),ProductMVP.ProductView {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var presenter: ProductMVP.ProductPresenter
    private lateinit var volleyHandler: SubCategoryVolleyHandler
    private lateinit var productAdapter: ProductAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao
    private lateinit var productDetailsFragment: ProductDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding= FragmentProductListBinding.inflate(layoutInflater)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle=arguments
        val subcategory_id=bundle?.getString("subcategory_id")
        initDb()
        initPresenter()
        initViews(subcategory_id!!)



    }

    private fun initDb() {
        appDatabase=AppDatabase.getAppDatabase(requireContext())
        cartDao=appDatabase.getCartDao()
    }

    private fun initPresenter() {
        volleyHandler = SubCategoryVolleyHandler(requireContext())
        presenter = ProductPresenter(volleyHandler, cartDao,this)
    }
    private fun initViews(subcategory_id: String){
        binding.productRecycler.layoutManager=LinearLayoutManager(requireContext())
        presenter.fetchProductData(subcategory_id)

    }

    override fun setResultProduct(productResponse: ProductResponse) {
        productAdapter=ProductAdapter(productResponse.products){
            val bundle=Bundle()
            bundle.putString("product_id",it)
            val fragment=ProductDetailsFragment()
            fragment.arguments=bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.containerHome,fragment)
                .addToBackStack(null).commit()
        }
        productAdapter.notifyDataSetChanged()
        binding.productRecycler.adapter=productAdapter
    }


    override fun onError(message: String) {

    }


}