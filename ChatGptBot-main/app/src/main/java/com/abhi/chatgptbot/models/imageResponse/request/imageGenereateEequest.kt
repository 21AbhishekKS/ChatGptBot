package com.abhi.chatgptbot.models.imageResponse.request

data class imageGenereateEequest(
    val n: Int,
    val prompt: String,
    val size: String
)