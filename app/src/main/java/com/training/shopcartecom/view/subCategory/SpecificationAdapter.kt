package com.training.shopcartecom.view.subCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.shopcartecom.databinding.ItemSpecificationsBinding
import com.training.shopcartecom.model.data.productdetails.Specification

class SpecificationAdapter(private val specificationList:List<Specification>):
    RecyclerView.Adapter<SpecificationAdapter.SpecificationViewHolder>() {

    inner class SpecificationViewHolder(val binding: ItemSpecificationsBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(specification:Specification){
            with(binding){
                specificationTitle.text = specification.title
                specificationValue.text = specification.specification
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSpecificationsBinding.inflate(layoutInflater,parent,false)
        return SpecificationViewHolder(binding)
    }

    override fun getItemCount() = specificationList.size

    override fun onBindViewHolder(holder: SpecificationViewHolder, position: Int) {
        holder.bind(specificationList[position])
    }
}