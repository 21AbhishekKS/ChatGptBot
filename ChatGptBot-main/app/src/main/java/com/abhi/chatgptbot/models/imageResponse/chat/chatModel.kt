package com.abhi.chatgptbot.models.imageResponse.chat

data class chatModel(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: Usage
)