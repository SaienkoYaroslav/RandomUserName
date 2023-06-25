package com.example.randomusername

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterUser(var usernameList: List<RandomUser>) :
    RecyclerView.Adapter<AdapterUser.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tv_item_user_name)
        fun bind(randomName: RandomUser) {
            textView.text = randomName.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_generated_user_name, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usernameList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val randomUsername = usernameList[position]
        holder.bind(randomUsername)
    }

    fun setList(list: List<RandomUser>) {
        usernameList = list
        notifyDataSetChanged()
    }

}