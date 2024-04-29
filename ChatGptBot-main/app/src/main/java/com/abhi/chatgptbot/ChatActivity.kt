package com.abhi.chatgptbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi.chatgptbot.adapter.MessageAdapter
import com.abhi.chatgptbot.api.ApiUtilities
import com.abhi.chatgptbot.databinding.ActivityChatBinding
import com.abhi.chatgptbot.models.imageResponse.request.chatrequst
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.MediaType
import okhttp3.RequestBody

class ChatActivity : AppCompatActivity() {


    lateinit var binding : ActivityChatBinding


    private lateinit var  adapter : MessageAdapter
    var list = ArrayList<MessageModel>()
    private  lateinit var mLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        adapter= MessageAdapter(list)


        mLayoutManager =LinearLayoutManager(this)
        mLayoutManager.stackFromEnd= true  //messages start form bottom not from top
        adapter = MessageAdapter(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = mLayoutManager

        binding.sendButton.setOnClickListener{

            if(binding.edtUsermsg.text.isEmpty()){
                Toast.makeText(this,"Plese Enter Your Prompt !",Toast.LENGTH_LONG).show()
            }

            else{
                callApi()
            }
        }



    }

    private fun callApi() {
        var message = binding.edtUsermsg.text.toString()
        binding.edtUsermsg.text!!.clear()

        list.add(MessageModel(true,false,message))


        adapter.notifyItemInserted(list.size - 1)  //item inserted at last position
        binding.recyclerView.recycledViewPool.clear() // to make  last msg only show no need to scroll
        binding.recyclerView.smoothScrollToPosition(list.size - 1) // scroll upto last item automatically

        val apiInterface = ApiUtilities.getApiinterface()
        val requestbody = RequestBody.create(MediaType.parse("application/json"),Gson().toJson(
            chatrequst(250,
                "text-davinci-003",
                message,
               0.7)
        ))

        val contentType = "application/json"
        val authorisation = "Bearer ${Utils.API_KEY}"

        lifecycleScope.launch( Dispatchers.IO ){

            try{
            val response = apiInterface.getchat(contentType,authorisation,requestbody)

            val textResponse = response.choices.first().text

            list.add(MessageModel(false,false,textResponse))

                withContext(Dispatchers.Main){
                    adapter.notifyItemInserted(list.size - 1)
                    binding.recyclerView.recycledViewPool.clear()
                    binding.recyclerView.smoothScrollToPosition(list.size - 1)
                }






            } catch (e : Exception){
                withContext(Dispatchers.Main){

                    Toast.makeText(this@ChatActivity,e.message,Toast.LENGTH_SHORT).show()

                }
            }
        }

    }
}