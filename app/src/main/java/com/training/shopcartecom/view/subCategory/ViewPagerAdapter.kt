package com.training.shopcartecom.view.subCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (
    private val data:List<SubCategoryData>, fragmentManager: FragmentActivity
):
    FragmentStateAdapter(fragmentManager){
    override fun getItemCount(): Int =data.size
    override fun createFragment(position: Int): Fragment{
        val bundle=Bundle()
        bundle.putString("subcategory_id",data[position].subcategory_id)
        val fragment=ProductListFragment()
        fragment.arguments=bundle
        return fragment
    }

}
