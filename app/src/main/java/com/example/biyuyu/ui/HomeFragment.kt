package com.example.biyuyu.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //Set fullscreen in the fragment
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //Set action bar
        (activity as AppCompatActivity).supportActionBar

        binding.userInfoHomeFragmentImage.setOnClickListener {
            goUserInfoFragmentFromHomeFragment()
        }

        binding.logoutHomeFragmentText.setOnClickListener {
            goInfoHomeFragmentFromHomeFragment()
        }

        return binding.root
    }

    // Launch UserInfroFragment from HomeFragment
    private fun goUserInfoFragmentFromHomeFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_userInfoFragment)
    }

    // Launch InfoHomeFragment from HomeFragment
    private fun goInfoHomeFragmentFromHomeFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_loginFragment)
    }
}