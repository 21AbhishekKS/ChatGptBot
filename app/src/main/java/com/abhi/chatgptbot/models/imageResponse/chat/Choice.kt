package com.abhi.chatgptbot.models.imageResponse.chat

data class Choice(
    val finish_reason: String,
    val index: Int,
    val logprobs: Any,
    val text: String
)