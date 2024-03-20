package com.training.shopcartecom.view.homedashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentHomeDashBoardBinding
import com.training.shopcartecom.view.ProfileFragment
import com.training.shopcartecom.view.ShoppingCartFragment
import com.training.shopcartecom.view.category.CategoryDashboardFragment

class HomeDashBoard : Fragment() {
    private lateinit var binding: FragmentHomeDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeDashBoardBinding.inflate(layoutInflater, container, false)
        changeHomeFragment(CategoryDashboardFragment())
        binding.homeMenu.setOnNavigationItemReselectedListener {
                menuItem ->
            when(menuItem.itemId){
                R.id.home -> changeHomeFragment(CategoryDashboardFragment())
                R.id.cart -> changeHomeFragment(ProfileFragment())
                R.id.account -> changeHomeFragment(ShoppingCartFragment())
            }
        }

        return binding.root
    }
    fun changeHomeFragment(fragment: Fragment){
        childFragmentManager.beginTransaction().add(R.id.containerHome,fragment).commit()
    }


}