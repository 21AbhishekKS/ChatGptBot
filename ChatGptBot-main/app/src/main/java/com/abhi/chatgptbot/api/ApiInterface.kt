package com.abhi.chatgptbot.api

import com.abhi.chatgptbot.models.imageResponse.chat.chatModel
import com.abhi.chatgptbot.models.imageResponse.generateImageModel
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {


    @POST("/v1/completions")
    suspend fun getchat(

        @Header("content-Type")contentType: String,
        @Header("Authorization") authorization : String,
        @Body requestBody: RequestBody
    ) : chatModel

    @POST("/v1/images/generations")
    suspend fun generaeImage(

        @Header("content-Type")contentType: String,
        @Header("Authorization") authorization : String,
        @Body requestBody: RequestBody
    ) : generateImageModel








    

}