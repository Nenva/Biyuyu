package com.example.biyuyu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentCreateRequestBinding
import com.example.biyuyu.models.Request
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.dialog_progress.*
import kotlinx.android.synthetic.main.fragment_create_request.*
import kotlinx.android.synthetic.main.request_layout.*
import java.util.*
import kotlin.collections.HashMap

class CreateRequestFragment : Fragment() {

    private lateinit var binding: FragmentCreateRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_create_request, container, false)

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

        // Click button to save data
        binding.buttonCreateRequest.setOnClickListener {
            var title = binding.createRequestTitle.text.toString()
            var description = binding.createRequestDescription.text.toString()
            saveRequestFirestore(title, description)
            goHomeFragmentFromCreateReportFragment()
        }

        return binding.root
    }

    private fun saveRequestFirestore(title: String, description: String) {
        val database = FirebaseFirestore.getInstance()
        val request: MutableMap<String, Any> = HashMap()
        request["title"] = title
        request["description"] = description

        database.collection("requests")
            .add(request)
            .addOnSuccessListener {
                FancyToast.makeText(context, "Su solicitud ha sido enviada",
                FancyToast.SUCCESS, FancyToast.LENGTH_SHORT, false).show()
            }
            .addOnFailureListener {
                FancyToast.makeText(context, "Lo sentimos, ha habido un error",
                    FancyToast.ERROR, FancyToast.LENGTH_SHORT, false).show()
            }
    }

    // Launch HomeFragment from CreateReportFragment
    private fun goHomeFragmentFromCreateReportFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createRequestFragment_to_homeFragment)
    }

    // Launch UserInfoFragment from CreateReportFragment
    private fun goUserInfoFragmentFromCreateReportFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createRequestFragment_to_userInfoFragment)
    }

    // Launch LoginFragment from CreateReportFragment
    private fun goLoginFragmentFromCreateReportFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_createRequestFragment_to_loginFragment)
    }
}