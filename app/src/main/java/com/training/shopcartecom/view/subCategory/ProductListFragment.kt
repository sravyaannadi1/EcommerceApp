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
import com.training.shopcartecom.presenter.subcategory.ProductMVP
import com.training.shopcartecom.presenter.subcategory.ProductPresenter
import com.training.shopcartecom.presenter.subcategory.SubCategoryMVP


class ProductListFragment : Fragment(),ProductMVP.ProductView {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var presenter: ProductMVP.ProductPresenter
    private lateinit var volleyHandler: SubCategoryVolleyHandler
    private lateinit var productAdapter: ProductAdapter

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
        initPresenter()
        initViews(subcategory_id!!)


    }

    private fun initPresenter() {
        volleyHandler = SubCategoryVolleyHandler(requireContext())
        presenter = ProductPresenter(volleyHandler, this)
    }
    private fun initViews(subcategory_id: String){
        binding.productRecycler.layoutManager=LinearLayoutManager(requireContext())
        presenter.fetchProductData(subcategory_id)

    }

    override fun setResultProduct(productList: List<ProductData>) {
        productAdapter=ProductAdapter(productList){
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