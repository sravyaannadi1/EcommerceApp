package com.training.shopcartecom.view.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.training.shopcartecom.databinding.ItemCategoryBinding

class CategoryAdapter(private val categories: List<CategoryData>, private val onClick:(String)->Unit):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
        inner class CategoryViewHolder(val binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(item: CategoryData ){
                binding.root.setOnClickListener {
                    onClick.invoke(item.category_id)
                }
                binding.category.text=item.category_name
                Picasso.get().load(item.category_image_url).into(binding.imageview1)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
      val binding=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val catItem=categories[position]
        return holder.bind(catItem)
    }

    override fun getItemCount(): Int=categories.size



}