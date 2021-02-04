package com.example.biyuyu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.biyuyu.databinding.FragmentCreateReportBinding

class CreateReportFragment : Fragment() {

    private lateinit var binding: FragmentCreateReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_report, container, false)

        (activity as AppCompatActivity).supportActionBar

        binding.homeFromCreateReportFragmentImage.setOnClickListener {
            goHomeFragment()
        }

        binding.userInfoCreateReportFragmentImage.setOnClickListener {
            goUserInfoFragment()
        }

        binding.logoutCreateReportFragmentText.setOnClickListener {
            goLoginFragment()
        }

        return binding.root
    }

    private fun goHomeFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createReportFragment_to_homeFragment)
    }

    private fun goUserInfoFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createReportFragment_to_userInfoFragment)
    }

    private fun goLoginFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createReportFragment_to_loginFragment)
    }
}