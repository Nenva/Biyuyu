package com.example.biyuyu.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.biyuyu.R
import com.example.biyuyu.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        // Set fullscreen in the fragment
        //requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.buttonRegister.setOnClickListener {
            registerUser()
        }

        binding.loginUserText.setOnClickListener {
            goLoginFragmentFromRegisterFragment()
        }

        return binding.root
    }

    private fun validateRegisterDetails(): Boolean {
        val names = register_insertNames.text.toString()
        val lastNames = register_insertLastNames.text.toString()
        val email = register_insertEmail.text.toString()
        val phone = register_insertPhoneNumber.text.toString()
        val town = register_insertTown.text.toString()
        val password = register_insertPassword.text.toString()
        val confirmPassword = register_confirmPassword.text.toString()
        return when {
            TextUtils.isEmpty(names.trim { it <= ' ' }) -> {
                FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(lastNames.trim { it <= ' ' }) -> {
                FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(phone.trim { it <= ' ' }) -> {
                FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(town.trim { it <= ' ' }) -> {
                FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(password.trim { it <= ' ' }) -> {
                FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(confirmPassword.trim { it <= ' ' }) -> {
                FancyToast.makeText(context, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            password.trim { it <= ' ' } != confirmPassword.trim { it <= ' ' } -> {
                FancyToast.makeText(context, resources.getString(R.string.password_must_be_equal_confirmPassword),
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            else -> {
                //FancyToast.makeText(context, resources.getString(R.string.dates_inserted_successfully), FancyToast.LENGTH_SHORT,
                //FancyToast.SUCCESS, false).show()
                true
            }
        }
    }

    // User registration
    private fun registerUser() {
        // Check with validate functions if the entries are valid or not
        if (validateRegisterDetails()) {
            val email: String = register_insertEmail.text.toString().trim { it <= ' ' }
            val password: String = register_insertPassword.text.toString().trim { it <= ' ' }

            // Create an instance and create a register a user with email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                    //If the registration is successfully done
                    if (task.isSuccessful) {
                        //Firebase registered user
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        FancyToast.makeText(context, resources.getString(R.string.dates_inserted_successfully),
                        FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
                        goHomeFragmentFromRegisterFragment()
                    } else {
                        //If the registering is not successful then show error message
                        FancyToast.makeText(context, resources.getString(R.string.user_register_failed),
                            FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
                    }
                })
        }
    }

    // Launch HomeFragment from RegisterFragment
    private fun goHomeFragmentFromRegisterFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_homeFragment)
    }

    // Launch LoginFragment from RegisterFragment
    private fun goLoginFragmentFromRegisterFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_loginFragment)
    }
}