package com.example.biyuyu.firestore

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.biyuyu.models.User
import com.example.biyuyu.ui.LoginFragment
import com.example.biyuyu.ui.RegisterFragment
import com.example.biyuyu.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val myFirestore = FirebaseFirestore.getInstance()

    fun saveUserInFirestore(registerFragment: RegisterFragment, userInfo: User) {

        // User is a collection name
        // If collection is already created, it will not create the same
        myFirestore.collection(Constants.USERS)
        // Document ID for users fields. Here the document it is the User ID
            .document(userInfo.id)
        // Here the userInfo are Field and the SetOption is set to merge.
        // It is for if we wants to merge later on instead of replacing the fields
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                // Here call a function of RegisterFragment for transferring the result to it
                registerFragment.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                registerFragment.hideProgressDialog()
                Log.e(
                    registerFragment.javaClass.simpleName,
                    "Ha ocurrido un error",
                    e
                )
            }
    }

    fun getCurrentUserID(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser
        // A variable to assign the currenUserID if it is not null or else it will be blank
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }

    fun getUserDetails(fragment: Fragment) {
        // Here we pass the collection name from which we wants the data
        myFirestore.collection(Constants.USERS)
        // The document id to get the Fields of user
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.i(fragment.javaClass.simpleName, document.toString())
                // Here we have received the document snapshot which is converted into the User data model object
                val user = document.toObject(User::class.java)!!
                val sharedPreferences =
                    fragment.activity?.getSharedPreferences(
                        Constants.BIYUYU_PREFERENCES,
                        Context.MODE_PRIVATE
                    )

                val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
                if (editor != null) {
                    editor.putString(
                        Constants.LOGGED_IN_USERNAME,
                        "${user.names} ${user.lastNames}"
                    )
                }
                if (editor != null) {
                    editor.apply()
                }

                // Pass the result to the LoginFragment
                when (fragment) {
                    is LoginFragment -> {
                        // Call a function for transferring the result to it
                        fragment.userLoggedInSuccess(user)
                    }
                }
            }
            .addOnFailureListener { e ->
                // Hide the progress dialog if there is any error. Print error in log
                when (fragment) {
                    is LoginFragment -> {
                        fragment.hideProgressDialog()
                    }
                }
            }
    }

}