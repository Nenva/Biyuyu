package com.example.biyuyu.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.biyuyu.R
import com.example.biyuyu.databinding.ActivityCreateRequestBinding
import com.example.biyuyu.firestore.FirestoreClass
import com.example.biyuyu.models.Request
import com.example.biyuyu.utils.Constants
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.dialog_progress.*

class CreateRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateRequestBinding
    private lateinit var myProgressDialog: Dialog
    private lateinit var firestoreClass: FirestoreClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_request)

        val customList = listOf("Taxi", "Pedido")
        val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, customList)
        binding.spinnerRequestType.adapter = adapter

        binding.spinnerRequestType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                FancyToast.makeText(this@CreateRequestActivity, "Has solicitado un ${parent?.getItemAtPosition(position).toString()}",
                FancyToast.INFO, FancyToast.LENGTH_SHORT, false).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        //Set action bar
        setSupportActionBar(binding.createReportActivityToolbar)

        binding.homeFromCreateReportActivity.setOnClickListener {
            goHomeActivityFromCreateReportActivity()
        }
        binding.logoutCreateReportActivity.setOnClickListener {
            goLoginActivityFromCreateReportActivity()
        }

        binding.buttonCreateRequest.setOnClickListener {
            saveRequestFirestore()
        }
    }

    private fun saveRequestFirestore() {
        val title = binding.createRequestTitle.text.toString().trim { it <= ' ' }
        val description = binding.createRequestDescription.text.toString().trim { it <= ' ' }
        val type = binding.spinnerRequestType.toString().trim { it <= ' ' }
        if (title.isEmpty() && description.isEmpty()) {
            FancyToast.makeText(
                this, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                FancyToast.ERROR, false
            ).show()
        } else {
            val database = FirebaseFirestore.getInstance()
            val request: MutableMap<String, Any> = HashMap()
            request["title"] = title
            request["description"] = description
            request["type"] = type

            database.collection(Constants.REQUESTS)
                .add(request)
                .addOnSuccessListener {
                    goHomeActivityFromCreateReportActivity()
                    FancyToast.makeText(this, "Su solicitud ha sido enviada",
                        FancyToast.SUCCESS, FancyToast.LENGTH_SHORT, false).show()
                }
                .addOnFailureListener {
                    FancyToast.makeText(this, "Lo sentimos, ha habido un error",
                        FancyToast.ERROR, FancyToast.LENGTH_SHORT, false).show()
                }
        }

    }

    /*private fun validateRequestEntries() {
        val title = binding.createRequestTitle.text.toString().trim { it <= ' ' }
        val description = binding.createRequestDescription.text.toString().trim { it <= ' ' }
        if (title.isEmpty() && description.isEmpty()) {
            FancyToast.makeText(
                this, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                FancyToast.ERROR, false
            ).show()
        }
    }*/

    private fun goHomeActivityFromCreateReportActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun goLoginActivityFromCreateReportActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    // Show progress dialog
    private fun showProgressDialog(text: String) {
        myProgressDialog = Dialog(this@CreateRequestActivity)
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