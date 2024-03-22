package com.training.shopcartecom.view.subCategory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.ItemProductBinding
import com.training.shopcartecom.model.data.Cart
import com.training.shopcartecom.model.data.Product

class ProductAdapter(private val products:List<Product>, private val onClick:(String)->Unit):
RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val cart=ArrayList<Cart>()
    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.root.setOnClickListener {
                onClick.invoke(item.product_id)
            }
            binding.productName.text = item.product_name
            binding.productDescription.text = item.description
            binding.productPrice.text = "$ ${item.price}"
            binding.ratingBar.rating=item.average_rating.toFloat()
            //Picasso.get().load(item.product_image_url).into(binding.imagePhone)
           // binding.imagePhone.setImageResource(R.drawable.baseline_phone_android_24)
            val cart=cart.find{
                it.id==item.product_id
            }
//            if((cart?.quantity?:0)>0){
//                binding.addToCart.isVisible=false
//                binding.quantityStepper.isVisible=true
//                binding.quantityStepper.setQuantity(cart?.quantity?:0)
//            }

        }

    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateCartItems(cart: List<Cart>){
        this.cart.clear()
        this.cart.addAll(cart)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater,parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }
}