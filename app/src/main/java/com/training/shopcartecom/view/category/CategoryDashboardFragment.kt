package com.training.shopcartecom.view.category

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentCategoryDashboardBinding
import com.training.shopcartecom.model.VolleyHandler.CategoryVolleyHandler
import com.training.shopcartecom.model.data.CategoryResponse
import com.training.shopcartecom.presenter.Category.CategoryMVP
import com.training.shopcartecom.presenter.Category.CategoryPresenter
import com.training.shopcartecom.view.subCategory.SubcategoryFragment

class CategoryDashboardFragment : Fragment(), CategoryMVP.CategoryView {
    private lateinit var binding: FragmentCategoryDashboardBinding
    private lateinit var presenter: CategoryPresenter
    //private lateinit var categorySelectionListener: OnSelectionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnSelectionListener) {
//            categorySelectionListener = context
//        } else {
//            throw ClassCastException("$context must implement OnSelectionListener")
//        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryDashboardBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategory()
        initViews()

        binding.recyclercategory.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun initViews() {
        presenter.fetchCategoryData()
    }

    private fun initCategory() {
        presenter = CategoryPresenter(CategoryVolleyHandler(requireContext()), this)
    }

    override fun setResultCategory(categoryResponse: List<CategoryData>) {
        val adapter = CategoryAdapter(categoryResponse){
            val bundle= Bundle()
            bundle.putString("category_id",it)
            val fragment=SubcategoryFragment()
            fragment.arguments=bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.containerHome,fragment).addToBackStack(null).commit()
        }
        binding.recyclercategory.adapter = adapter
    }



    override fun onError(message: String) {

    }


}

