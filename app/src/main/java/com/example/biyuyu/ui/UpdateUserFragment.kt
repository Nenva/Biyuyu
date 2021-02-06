package com.example.biyuyu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentUpdateUserBinding

class UpdateUserFragment : Fragment() {

    private lateinit var binding: FragmentUpdateUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_user, container, false)

        //Set fullscreen in the fragment
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //Set action bar
        (activity as AppCompatActivity).supportActionBar

        binding.homeFromUserInfoUpdateFragmentImage.setOnClickListener {
            goHomeFragmentFromUpdateUserFragment()
        }

        binding.logoutUpdateUserFragmentText.setOnClickListener {
            goLoginFragmentFromUpdateUserFragment()
        }

        binding.buttonUpdateUser.setOnClickListener {
            goHomeFragmentFromUpdateUserFragment()
        }

        return binding.root
    }

    // Launch HomeFragment from UpdateUserFragment
    private fun goHomeFragmentFromUpdateUserFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_updateUserFragment_to_homeFragment)
    }

    // Launch LoginFragment from UpdateUserFragment
    private fun goLoginFragmentFromUpdateUserFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_updateUserFragment_to_loginFragment)
    }
}