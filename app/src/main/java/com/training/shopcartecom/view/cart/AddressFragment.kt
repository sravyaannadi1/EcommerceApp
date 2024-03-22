package com.training.shopcartecom.view.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentAddressBinding

interface AddAddressClickListener{
    fun onAddClicked(title:String, address:String)
}
class AddressFragment(val addAddressClickListener:AddAddressClickListener) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {

            val title = binding.etTitleAddress.text.toString()
            val address = binding.etTitleBody.text.toString()

            if(title.isNotEmpty() && address.isNotEmpty()) {

                addAddressClickListener.onAddClicked(title = title, address = address)
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

}