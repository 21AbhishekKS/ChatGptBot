package com.abhi.chatgptbot.models.imageResponse.chat

data class Usage(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
)