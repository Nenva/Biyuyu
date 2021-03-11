package com.example.biyuyu.models

data class Request(
    val id: String,
    val user: String,
    var title: String,
    var description: String
)