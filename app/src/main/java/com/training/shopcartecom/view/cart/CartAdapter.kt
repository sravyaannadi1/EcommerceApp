package com.training.shopcartecom.view.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.training.shopcartecom.databinding.ItemCartBinding
import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.Constants
import com.training.shopcartecom.view.subCategory.ItemClickListener

class CartAdapter(private val cart:List<Cart>, val itemClickListener: ItemClickListener,
                  private val showQuantityStepper: Boolean=true): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

                      inner class CartViewHolder(val binding:ItemCartBinding):RecyclerView.ViewHolder(binding.root){
                          fun bind(cart: Cart){
                              with(binding){
                                  binding.productName.text=cart.product_name
                                  binding.productPrice.text=cart.price
                                  binding.productDescription.text=cart.description
                                  binding.tvPriceTotal.text=cart.unitPrice
                                  quantityStepper.setQuantity(cart.quantity)
                                  val imageUrl="${Constants.BASE_URL_IMAGE}${cart.product_image_url}"
                                  Picasso.get().load(imageUrl).into(binding.imagePhone)
                                  quantityStepper.isVisible=showQuantityStepper
                                  binding.root.setOnClickListener {
                                      itemClickListener.isSelected(cart.id)
                                  }

                              }
                          }

                      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ItemCartBinding.inflate(layoutInflater,parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cart[position])
    }

    override fun getItemCount(): Int =cart.size
}