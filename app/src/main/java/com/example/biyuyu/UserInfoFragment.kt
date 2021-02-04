package com.example.biyuyu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.biyuyu.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {

    private lateinit var binding: FragmentUserInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)

        (activity as AppCompatActivity).supportActionBar

        binding.homeFromUserInfoFragmentImage.setOnClickListener {
            goHomeFragment()
        }

        binding.logoutUserInfoFragmentText.setOnClickListener {
            goLoginFragment()
        }

        return binding.root
    }

    private fun goHomeFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_userInfoFragment_to_homeFragment)
    }

    private fun goLoginFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_userInfoFragment_to_loginFragment)
    }
}