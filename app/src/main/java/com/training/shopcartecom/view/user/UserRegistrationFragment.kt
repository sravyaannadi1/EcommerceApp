package com.training.shopcartecom.view.user

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentUserRegistrationBinding
import com.training.shopcartecom.model.data.UserData
import com.training.shopcartecom.model.VolleyHandler.UserVolleyHandler
import com.training.shopcartecom.presenter.User.RegisterMVP
import com.training.shopcartecom.presenter.User.RegisterPresenter
import com.training.shopcartecom.view.homedashboard.HomeDashBoard


class UserRegistrationFragment : Fragment(), RegisterMVP.RegisterView {
    private lateinit var binding: FragmentUserRegistrationBinding
    private lateinit var sharedPref: SharedPreferences
private lateinit var presenter: RegisterPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRegistrationBinding.inflate(inflater, container, false)

        binding.signin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container1, UserLoginFragment()).commit()
        }


        initPref()
        presenter= RegisterPresenter(UserVolleyHandler(requireContext()),this)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.signup.setOnClickListener {
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val phone = binding.mobileNumber.text.toString()
            val password = binding.password.text.toString()
            val repeatPassword = binding.rePassword.text.toString()

            if (password != repeatPassword) {
                showToast("Passwords must match")
            } else {
                presenter.sendRegisterData(UserData( username, email, phone, password))
            }
        }
    }

    override fun setResultRegister(status: Int, message: String) {
        if(status==0){
            showToast("registration successful")
            val userData = UserData(
                email_id = binding.email.text.toString(),
                full_name = binding.username.text.toString(),
                mobile_no = binding.mobileNumber.text.toString(),
                password = binding.password.text.toString()
            )
            saveToPref(userData)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container1, UserLoginFragment()).commit()
        }

        else {
            showToast("Registration failed: $message")
        }
    }


    private fun saveToPref(userData: UserData) {
        sharedPref.edit().apply {
            putString(KEY_USERNAME, userData.full_name)
            putString(KEY_EMAIL, userData.email_id)
            putString(KEY_PHONE, userData.mobile_no)
            putString(KEY_PASSWORD, userData.password)
        }.apply()

        if (sharedPref.contains(KEY_USERNAME)) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container1, UserLoginFragment()).commit()
        } else {
            showToast("Registration unsuccessful")        }
    }

    private fun initPref() {
        sharedPref = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val PREF_NAME = "Register"
        const val KEY_USERNAME = "username"
        const val KEY_EMAIL = "email"
        const val KEY_PHONE = "phone"
        const val KEY_PASSWORD = "password"
    }
}