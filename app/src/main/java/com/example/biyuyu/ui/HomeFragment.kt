package com.example.biyuyu.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentHomeBinding
import com.example.biyuyu.utils.Constants
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val sharedPreferences = this.activity?.getSharedPreferences(
            Constants.BIYUYU_PREFERENCES,
            Context.MODE_PRIVATE
        )
        val username = sharedPreferences?.getString(Constants.LOGGED_IN_USERNAME, "")!!
        binding.textHome.text = "Bienvenido, $username"
        //Set fullscreen in the fragment
        //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //Set action bar
        (activity as AppCompatActivity).supportActionBar

        binding.userInfoHomeFragmentImage.setOnClickListener {
            goUserInfoFragmentFromHomeFragment()
        }

        binding.logoutHomeFragmentText.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            goLoginFragmentFromHomeFragment()
        }

        binding.fabCreateReport.setOnClickListener {
            goCreateReportFragmentFromHomeFragment()
        }

        return binding.root
    }

    // Launch CreateReportFragment from HomeFragment
    private fun goCreateReportFragmentFromHomeFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_createReportFragment)
    }

    // Launch UserInfoFragment from HomeFragment
    private fun goUserInfoFragmentFromHomeFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_userInfoFragment)
    }

    // Launch InfoHomeFragment from HomeFragment
    private fun goLoginFragmentFromHomeFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_loginFragment)
    }
}