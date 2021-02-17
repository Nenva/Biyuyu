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
import com.example.biyuyu.databinding.FragmentCreateReportBinding

class CreateReportFragment : Fragment() {

    private lateinit var binding: FragmentCreateReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_create_report, container, false)

        //Set fullscreen in the fragment
        //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //Set action bar
        (activity as AppCompatActivity).supportActionBar

        binding.homeFromCreateReportFragmentImage.setOnClickListener {
            goHomeFragmentFromCreateReportFragment()
        }

        binding.userInfoCreateReportFragmentImage.setOnClickListener {
            goUserInfoFragmentFromCreateReportFragment()
        }

        binding.logoutCreateReportFragmentText.setOnClickListener {
            goLoginFragmentFromCreateReportFragment()
        }

        return binding.root
    }

    // Launch HomeFragment from CreateReportFragment
    private fun goHomeFragmentFromCreateReportFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createReportFragment_to_homeFragment)
    }

    // Launch UserInfoFragment from CreateReportFragment
    private fun goUserInfoFragmentFromCreateReportFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createReportFragment_to_userInfoFragment)
    }

    // Launch LoginFragment from CreateReportFragment
    private fun goLoginFragmentFromCreateReportFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createReportFragment_to_loginFragment)
    }
}