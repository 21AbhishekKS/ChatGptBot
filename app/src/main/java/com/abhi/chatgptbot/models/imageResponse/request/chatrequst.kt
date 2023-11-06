package com.abhi.chatgptbot.models.imageResponse.request

data class chatrequst(
    val max_tokens: Int,
    val model: String,
    val prompt: String,
    val temperature: Double
)