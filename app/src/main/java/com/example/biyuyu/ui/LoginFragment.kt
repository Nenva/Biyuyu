 package com.example.biyuyu.ui

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentLoginBinding

 class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        // Set fullscreen in the fragment
        //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.loginButton.setOnClickListener {
            goHomeFragmentFromLoginFragment()
        }

        binding.registerUserText.setOnClickListener {
            goRegisterFragmentFromLoginFragment()
        }

        return binding.root
    }

     // Launch HomeFragment from LoginFragment
     private fun goHomeFragmentFromLoginFragment() {
         Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment)
     }

     // Launch RegisterFragment from LoginFragment
     private fun goRegisterFragmentFromLoginFragment() {
         Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
     }
}