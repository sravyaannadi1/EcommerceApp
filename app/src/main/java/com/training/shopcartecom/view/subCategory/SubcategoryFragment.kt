package com.training.shopcartecom.view.subCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentSubcategoryBinding
import com.training.shopcartecom.model.VolleyHandler.SubCategoryVolleyHandler
import com.training.shopcartecom.model.callbacks.OperationalCallBack
import com.training.shopcartecom.presenter.subcategory.SubCategoryMVP
import com.training.shopcartecom.presenter.subcategory.SubCategoryPresenter


class SubcategoryFragment : Fragment() {
 private lateinit var binding: FragmentSubcategoryBinding
 private lateinit var presenter: SubCategoryMVP.SubCategoryPresenter
 private lateinit var volleyHandler: SubCategoryVolleyHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSubcategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle=arguments
        val category_id=bundle?.getString("category_id")
        volleyHandler= SubCategoryVolleyHandler(requireContext())
        presenter=SubCategoryPresenter(volleyHandler,object :SubCategoryMVP.SubCategoryView{
            override fun setResultSubCategory(subcategoryList: List<SubCategoryData>) {
                val fragment=ProductListFragment()
                binding.viewPager.adapter=ViewPagerAdapter(subcategoryList,requireActivity())
                TabLayoutMediator(binding.tabLayout,binding.viewPager){
                        tab,position->
                    tab.text=subcategoryList[position].subcategory_name
                }.attach()
            }

            override fun onError(message: String) {
            }
        })
        presenter.fetchSubCategoryData(category_id!!)


    }



}