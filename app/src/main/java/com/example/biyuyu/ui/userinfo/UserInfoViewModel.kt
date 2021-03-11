package com.example.biyuyu.ui.userinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInfoViewModel: ViewModel() {

    private val textUserInfo = MutableLiveData<String>().apply {
        value = "Esta es la sección de información de usuario"
    }
    val text: LiveData<String> = textUserInfo
}