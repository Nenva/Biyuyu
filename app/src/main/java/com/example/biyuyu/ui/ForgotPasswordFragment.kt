package com.example.biyuyu.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.dialog_progress.*
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var myProgressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false)

        // Send a new password to email inserted
        binding.buttonForgotPassword.setOnClickListener {
            val email = forgotPassword_insertEmail.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                FancyToast.makeText(context, resources.getString(R.string.fill_all_fields),
                FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            } else {
                showProgressDialog(resources.getString(R.string.please_wait))
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{ task ->
                        hideProgressDialog()
                        if (task.isSuccessful) {
                            FancyToast.makeText(context, "La nueva contraseña se ha enviado",
                            FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
                            goLoginFragmentFromForgotPasswordFragment()
                        } else {
                            FancyToast.makeText(context, resources.getString(R.string.login_unsuccess),
                                FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
                        }
                    }
            }
        }

        binding.textBackLogin.setOnClickListener {
            goLoginFragmentFromForgotPasswordFragment()
        }

        return binding.root
    }

    // Launch LoginFragment from ForgotPasswordFragment
    private fun goLoginFragmentFromForgotPasswordFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
    }

    // Show progress dialog
    private fun showProgressDialog(text: String) {
        myProgressDialog = Dialog(requireContext())
        myProgressDialog.setContentView(R.layout.dialog_progress)
        myProgressDialog.text_progress_text.text = text
        myProgressDialog.setCancelable(false)
        myProgressDialog.setCanceledOnTouchOutside(false)
        myProgressDialog.show()
    }

    // Hide progress dialog
    private fun hideProgressDialog() {
        myProgressDialog.dismiss()
    }
}