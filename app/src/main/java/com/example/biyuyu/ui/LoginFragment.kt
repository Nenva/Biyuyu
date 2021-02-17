 package com.example.biyuyu.ui

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentLoginBinding
import com.example.biyuyu.firestore.FirestoreClass
import com.example.biyuyu.models.User
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.dialog_progress.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

 class LoginFragment : Fragment(), View.OnClickListener {

     private lateinit var binding: FragmentLoginBinding
     private lateinit var myProgressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        // Set fullscreen in the fragment
        //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Click event assigned to following views
        binding.textForgotPassword.setOnClickListener(this)
        binding.loginButton.setOnClickListener(this)
        binding.registerUserText.setOnClickListener(this)

        return binding.root
    }

     // Set click events of LoginFragment
     override fun onClick(view: View?) {
         if (view != null) {
             when (view.id) {
                 R.id.text_forgotPassword -> {
                     goForgotPasswordFragmentFromLoginFragment()
                 }
                 R.id.login_button -> {
                     loginRegisteredUser()
                 }
                 R.id.registerUser_text -> {
                     goRegisterFragmentFromLoginFragment()
                 }
             }
         }
     }

     //
     fun userLoggedInSuccess(user: User) {
         // Hide the progress dialog
         hideProgressDialog()
         // Print the use details in the log as of now
         Log.i("Names: ", user.names)
         Log.i("Last Names: ", user.lastNames)
         Log.i("Email: ", user.email)
         Log.i("Phone Number: ", user.phoneNumber)
         Log.i("Town: ", user.town)
         // Redirect he user to Main Screen after login
         goHomeFragmentFromLoginFragment()
     }

     // Login user registered
     private fun loginRegisteredUser() {
         if (validateLoginDetails()) {
             //Show the progress dialog
             showProgressDialog(resources.getString(R.string.please_wait))
             // Get the text from editText and trim the space
             val email = login_insertEmail.text.toString().trim { it <= ' ' }
             val password = login_insertPassword.text.toString().trim { it <= ' ' }
             // Login using FirebaseAuth
             FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                 .addOnCompleteListener { task ->
                     if (task.isSuccessful) {
                         FirestoreClass().getUserDetails(this@LoginFragment)
                         //goHomeFragmentFromLoginFragment()
                         //FancyToast.makeText(context, resources.getString(R.string.login_success),
                         //FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
                     } else {
                         hideProgressDialog()
                         FancyToast.makeText(context, resources.getString(R.string.login_unsuccess),
                             FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
                     }
                 }
         }
     }

     // Validate login entries
     private fun validateLoginDetails(): Boolean {
         val email = login_insertEmail.text.toString().trim { it <= ' ' }
         val password = login_insertPassword.text.toString().trim { it <= ' ' }
         return when {
             TextUtils.isEmpty(email) -> {
                 FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                     FancyToast.ERROR, false).show()
                 false
             }
             TextUtils.isEmpty(password) -> {
                 FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                     FancyToast.ERROR, false).show()
                 false
             }
             else -> true
         }
     }

     // Launch HomeFragment from LoginFragment
     private fun goHomeFragmentFromLoginFragment() {
         Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment)
     }

     // Launch RegisterFragment from LoginFragment
     private fun goRegisterFragmentFromLoginFragment() {
         Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
     }

     // Launch ForgotPasswordFragment from LoginFragment
     private fun goForgotPasswordFragmentFromLoginFragment() {
         Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
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
     fun hideProgressDialog() {
         myProgressDialog.dismiss()
     }
 }