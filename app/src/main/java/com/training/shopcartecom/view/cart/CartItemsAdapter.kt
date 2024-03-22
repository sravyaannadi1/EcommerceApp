package com.training.shopcartecom.view.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.training.shopcartecom.databinding.ItemViewproductsBinding
import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.Constants

class CartItemsAdapter (private val cartList: List<Cart>): RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder>() {

    inner class CartItemViewHolder(val binding: ItemViewproductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cartItem: Cart) {
            with(binding) {
                productName.text = cartItem.product_name
                productPrice.text = "$ ${cartItem.price}"
                productUnitPrice.text = "$ ${cartItem.unitPrice}"
                productQuantity.text = cartItem.quantity.toString()
                val url = "${Constants.BASE_URL_IMAGE}${cartItem.product_image_url}"
                Picasso.get().load(url).into(imagePhone)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CartItemViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewproductsBinding.inflate(layoutInflater, parent, false)
        return CartItemViewHolder(binding)
    }

    override fun getItemCount() = cartList.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(cartList[position])
    }
}