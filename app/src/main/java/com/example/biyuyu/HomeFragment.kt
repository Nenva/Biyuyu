package com.example.biyuyu

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.biyuyu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        (activity as AppCompatActivity).supportActionBar

        binding.userInfoHomeFragmentImage.setOnClickListener {
            goUserInfoFragment()
        }

        binding.logoutHomeFragmentText.setOnClickListener {
            goInfoHomeFragment()
        }

        return binding.root
    }

    private fun goUserInfoFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_userInfoFragment)
    }

    private fun goInfoHomeFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_loginFragment)
    }
}