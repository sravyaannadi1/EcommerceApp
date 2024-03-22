package com.training.shopcartecom.view.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.shopcartecom.databinding.ItemDeliveryBinding
import com.training.shopcartecom.model.data.order.Addresse

interface IsSelectedListener{
    fun saveAddress(address: Addresse)
}
class DeliveryAdapter(private val addressList:List<Addresse>, val isSelectedListener: IsSelectedListener):
    RecyclerView.Adapter<DeliveryAdapter.AddressViewHolder>() {

    var selectedPosition: Int = -1
        set(value) {
            val old = selectedPosition
            field = value
            notifyItemChanged(selectedPosition) // set current item to checked
            notifyItemChanged(old) // Set previous item to unchecked
        }

    inner class AddressViewHolder(val binding: ItemDeliveryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(address: Addresse){
            with(binding){
                radioBtn.isChecked = adapterPosition == selectedPosition
                tvAddressTitle.text = address.title
                tvAddressDesc.text = address.address
                radioBtn.isClickable = false
                root.setOnClickListener {
                    selectedPosition = adapterPosition
                    isSelectedListener.saveAddress(address)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDeliveryBinding.inflate(layoutInflater,parent,false)
        return AddressViewHolder(binding)
    }

    override fun getItemCount() = addressList.size

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(addressList[position])
    }
}