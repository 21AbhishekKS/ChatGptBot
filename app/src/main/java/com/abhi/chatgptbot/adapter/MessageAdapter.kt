package com.abhi.chatgptbot.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abhi.chatgptbot.MessageModel
import com.abhi.chatgptbot.R
import com.bumptech.glide.Glide

class MessageAdapter(val list : ArrayList<MessageModel>)  : Adapter<MessageAdapter.MessageviewHolder>() {

    inner class MessageviewHolder(view : View) : ViewHolder(view) {

        val messageTxt = view.findViewById<TextView>(R.id.show_message)
        val imageContainer = view.findViewById<LinearLayout>(R.id.imageCard)
        val image = view.findViewById<ImageView>(R.id.image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageviewHolder {

        var view : View ?= null
        var from = LayoutInflater.from(parent.context)

        if(viewType == 0){
            view = from.inflate(R.layout.user_chat,parent,false)

        }else{
            view = from.inflate(R.layout.bot_chat,parent,false)

        }

        return  MessageviewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val message =list[position]
        return if(message.isUSer) return 0 else return 1
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MessageviewHolder, position: Int) {

        val message = list[position]

        if(!message.isUSer){
            holder.imageContainer.visibility = GONE
        }

        if (message.isImage){

            holder.imageContainer.visibility = VISIBLE
            Glide.with(holder.itemView.context).load(message.message).into(holder.image)

        }else{
            holder.messageTxt.text = message.message
        }
    }
}