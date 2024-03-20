package com.training.shopcartecom.view.user

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.training.shopcartecom.view.category.CategoryDashboardFragment
import com.training.shopcartecom.R
import com.training.shopcartecom.databinding.FragmentHomeDashBoardBinding
import com.training.shopcartecom.databinding.FragmentUserLoginBinding
import com.training.shopcartecom.model.VolleyHandler.UserVolleyHandler
import com.training.shopcartecom.model.data.LoginData
import com.training.shopcartecom.presenter.User.LoginPresenter
import com.training.shopcartecom.presenter.User.MVPLogin
import com.training.shopcartecom.view.homedashboard.HomeDashBoard


class UserLoginFragment : Fragment(), MVPLogin.LoginView {
    private lateinit var binding: FragmentUserLoginBinding
    private lateinit var sharedPref:SharedPreferences
    private lateinit var presenter: LoginPresenter
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUserLoginBinding.inflate(layoutInflater,container,false)
        initPref()
        presenter= LoginPresenter(UserVolleyHandler(requireContext()),this)
        initViews()
        binding.signup.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.container1,
                UserRegistrationFragment()
            ).addToBackStack(null).commit()
        }
        //checkLoginStatus()
        return binding.root
        // Inflate the layout for this fragment
    }

    private fun initViews() {
        binding.signin.setOnClickListener {
            val emailFromReg=sharedPref.getString("email", "")
            val passwordfromReg= sharedPref.getString("password", "")

            val email = binding.email.getText().toString()
            val password = binding.password.getText().toString()
            if (email.isNotEmpty() && password.isNotEmpty() && (email == emailFromReg ) && (password==passwordfromReg) ) {
                presenter.sendLoginData(LoginData(email,password))
            } else {
                Toast.makeText(context, "Userid or password incorrect", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun setResultLogin(status: Int, message: String) {
        if(status==0){
            showToast("Logged in Successfully")
            //saveLoginState(true)
            navigateToDashboard()
        }
        else
        {
            showToast("Email id or password incorrect")
        }
    }
    private fun saveToPref(loginData: LoginData){

        sharedPref.edit().apply {
            putString("email", loginData.email_id)
            putString("password", loginData.password)
        }.apply()
    }
    private fun saveLoginState(isLoggedIn: Boolean) {
        sharedPref.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    private fun checkLoginStatus() {
        if (isLoggedIn()) {
            navigateToDashboard()
        }
    }
    private fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean("isLoggedIn", false)
    }

    private fun initPref()
{
    sharedPref=requireActivity().getSharedPreferences("Register",MODE_PRIVATE)
    editor=sharedPref.edit()
}
    private fun navigateToDashboard() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container1, HomeDashBoard())
            .commit()
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}