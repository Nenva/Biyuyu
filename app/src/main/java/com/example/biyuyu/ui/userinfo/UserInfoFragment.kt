package com.example.biyuyu.ui.userinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {

    private lateinit var binding: FragmentUserInfoBinding
    private lateinit var userInfoViewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)

        userInfoViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textUserInfo.text = it
        })
        //Set fullscreen in the fragment
        //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //Set action bar
        (activity as AppCompatActivity).supportActionBar

        binding.homeFromUserInfoFragmentImage.setOnClickListener {
            goHomeFragmentFromUserInfoFragment()
        }

        binding.logoutUserInfoFragmentText.setOnClickListener {
            goLoginFragmentFromUserInfoFragment()
        }

        binding.fabUpdateUser.setOnClickListener {
            goUpdateUserFragmentFromUserInfoFragment()
        }

        return binding.root
    }

    // Launch UpdateUserFragment from UserInfoFragment
    private fun goUpdateUserFragmentFromUserInfoFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_userInfoFragment_to_updateUserFragment)
    }

    // Launch HomeFragment from UserInfoFragment
    private fun goHomeFragmentFromUserInfoFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_userInfoFragment_to_homeFragment)
    }

    // Launch LoginFragment from UserInfoFragment
    private fun goLoginFragmentFromUserInfoFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_userInfoFragment_to_loginFragment)
    }
}