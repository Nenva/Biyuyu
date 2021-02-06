package com.example.biyuyu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        // Set fullscreen in the fragment
        //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.buttonRegister.setOnClickListener {
            goHomeFragmentFromRegisterFragment()
        }

        binding.loginUserText.setOnClickListener {
            goLoginFragmentFromRegisterFragment()
        }

        return binding.root
    }

    // Launch HomeFragment from RegisterFragment
    private fun goHomeFragmentFromRegisterFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_homeFragment)
    }

    // Launch LoginFragment from RegisterFragment
    private fun goLoginFragmentFromRegisterFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_loginFragment)
    }
}