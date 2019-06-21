package com.example.geniusplazausers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class UserRecyclerviewAdapter(private val users: List<User>?): RecyclerView.Adapter<UserRecyclerviewAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,viewType,parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            users?.let {
                holder.bind(users[position])
            }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.user_item
    }

    inner class UserViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
        val bind = {obj : User ->
            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings()
        }
    }
}

