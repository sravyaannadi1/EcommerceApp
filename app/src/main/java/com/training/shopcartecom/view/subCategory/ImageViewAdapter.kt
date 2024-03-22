package com.training.shopcartecom.view.subCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.training.shopcartecom.databinding.ItemImagesBinding
import com.training.shopcartecom.databinding.ItemReviewsBinding
import com.training.shopcartecom.model.data.Constants
import com.training.shopcartecom.model.data.productdetails.Image
import com.training.shopcartecom.model.data.productdetails.Review

class ImageViewAdapter(private val images:List<Image>, val context: Context): RecyclerView.Adapter<ImageViewAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, position: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemImagesBinding.inflate(layoutInflater, container, false)
        return ImageViewHolder(binding)
    }
    override fun getItemCount()= images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int){
        holder.bind(images[position])
    }
    inner class ImageViewHolder(val binding:ItemImagesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(image:Image){
            Picasso.get().load("${Constants.BASE_URL_IMAGE}${image.image}").into(binding.imageFlipper)

        }
    }
}
