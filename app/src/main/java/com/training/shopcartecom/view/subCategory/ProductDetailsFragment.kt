package com.training.shopcartecom.view.subCategory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.training.shopcartecom.MainActivity
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentProductDetailsBinding
import com.training.shopcartecom.model.VolleyHandler.SubCategoryVolleyHandler
import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.productdetails.ProductDescriptionResponse
import com.training.shopcartecom.model.databasecart.CartDao
import com.training.shopcartecom.presenter.subcategory.ProductDetailsMVP
import com.training.shopcartecom.presenter.subcategory.ProductDetailsPresenter
import com.training.shopcartecom.view.QuantityStepperListener
import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.shopcartecom.model.data.productdetails.Product
import com.training.shopcartecom.model.data.productdetails.Review
import com.training.shopcartecom.model.databasecart.AppDatabase


class ProductDetailsFragment : Fragment() {

    private lateinit var binding:FragmentProductDetailsBinding
    private lateinit var presenter: ProductDetailsPresenter
    private lateinit var cartDao: CartDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var viewAdapter: ImageViewAdapter
    private lateinit var volleyHandler: SubCategoryVolleyHandler
    private var productId:String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = arguments?.getString("product_id")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val localBroadcastManager = LocalBroadcastManager.getInstance(requireContext())
        val intent = Intent("Quantity_Update")
        initDB()
        presenter = ProductDetailsPresenter(SubCategoryVolleyHandler.getInstance(requireContext()), cartDao,object:ProductDetailsMVP.ProductDeatailsView{
            override fun onError(message: String) {

            }

            @SuppressLint("SetTextI18n")
            override fun setResultProduct(productDescriptionResponse: ProductDescriptionResponse) {
                //productDescriptionResponse
                with(binding){
                    productDName.text = productDescriptionResponse.product.product_name
                    productDDescription.text = productDescriptionResponse.product.description
                    ratingBar.rating = productDescriptionResponse.product.average_rating.toFloat()
                    productDPrice.text = "$ ${productDescriptionResponse.product.price}"
//
                }

                binding.addToCart.setOnClickListener {
                    with(productDescriptionResponse.product){
                        addToCart(this, 1)
                    }
                    it.isVisible = false
                    binding.quantityStepper.isVisible = true
                }

                presenter.fetchCartwithId(productDescriptionResponse.product.product_id)?.let {
                    binding.quantityStepper.setQuantity(it.quantity)
                    binding.addToCart.isVisible = false
                    binding.quantityStepper.isVisible = true
                }

                binding.quantityStepper.setQuantityStepperListener(object :
                    QuantityStepperListener {
                    override fun onQuantityChanged(quantity: Int) {
                        with(productDescriptionResponse.product){
                            localBroadcastManager.sendBroadcast(intent)
                            addToCart(this, quantity)
                        }

                    }

                    override fun onQuantityZero() {
                        with(productDescriptionResponse.product){
                            val cartItem = Cart(
                                id=product_id,
                                unitPrice = price,
                                product_image_url= product_image_url,
                                product_name = product_name,
                                description = description

                            )
                            localBroadcastManager.sendBroadcast(intent)
                            presenter.deleteItem(cartItem)
                        }
                        binding.addToCart.isVisible = true
                        binding.quantityStepper.isVisible = false
                    }

                })

                val adapter = SpecificationAdapter(productDescriptionResponse.product.specifications)
                binding.rvSpecifications.layoutManager = LinearLayoutManager(requireContext())
                binding.rvSpecifications.adapter = adapter

                val reviewAdapter = ReviewsAdapter(productDescriptionResponse.product.reviews)
                binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
                binding.rvReviews.adapter = reviewAdapter

                viewAdapter = ImageViewAdapter(productDescriptionResponse.product.images, requireContext())
                binding.viewPagerImage.adapter = viewAdapter

            }



        })

        productId?.let{
            presenter.fetchProductDetails(it)
        }
    }

    private fun initDB() {
        val database = AppDatabase.getAppDatabase(requireContext())
        cartDao = database.getCartDao()
    }

    override fun onResume() {
        super.onResume()
    }

    fun addToCart(product: Product, quantity:Int){
        with(product) {
            val cartItem = Cart(
                id = product_id,
                unitPrice = price,
                product_image_url = product_image_url,
                product_name = product_name,
                description = description,
                quantity = quantity

            )
            presenter.addCart(cartItem)
        }
    }

}