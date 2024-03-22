package com.training.shopcartecom.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentCheckoutBinding
import com.training.shopcartecom.databinding.ItemCheckoutfragmentBinding


class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var checkoutViewPageAdapter: CheckoutViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){

        val fragments = listOf(ViewProductsFragment(), DeliveryFragment(), PaymentFragment(), SummaryFragment())

        checkoutViewPageAdapter  = CheckoutViewPagerAdapter(fragments, this)

        with(binding){
            viewPager.adapter = checkoutViewPageAdapter

            TabLayoutMediator(tabLayout, viewPager){
                    tab, position ->
                val tabBinding = ItemCheckoutfragmentBinding.inflate(layoutInflater,tabLayout,false)
                with(tabBinding){
                    when(position){
                        0 ->{
                            tvTextView.text = getString(R.string.cart_items)
                        }
                        1 ->{
                            tvTextView.text = getString(R.string.delivery)
                        }
                        2 ->{
                            tvTextView.text = getString(R.string.payment)
                        }
                        3 ->{
                            tvTextView.text = getString(R.string.summary)
                        }
                    }

                }
                tab.customView = tabBinding.root
            }.attach()
        }


    }

    fun moveToNext(position:Int){
        binding.viewPager.setCurrentItem(position,true)
    }

}