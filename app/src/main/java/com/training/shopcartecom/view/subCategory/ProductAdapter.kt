package com.training.shopcartecom.view.subCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.training.shopcartecom.databinding.ItemProductBinding

class ProductAdapter(private val products:List<ProductData>,private val onClick:(String)->Unit):
RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductData) {
            binding.root.setOnClickListener {
                onClick.invoke(item.product_id)
            }
            binding.productName.text = item.product_name
            binding.productDesc.text = item.description
            binding.productPrice.text = item.price
            //Picasso.get().load(item.product_image_url).into(binding.productimg)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productItem= products[position]
        return holder.bind(productItem)
    }

    override fun getItemCount(): Int=products.size



}