package com.example.favorito.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.favorito.R
import com.example.favorito.data.UsersFavEntity
import kotlinx.android.synthetic.main.item_list_github_person.view.*

class MainAdapter(var data: List<UsersFavEntity> = listOf(), val clickListener: (UsersFavEntity) -> Unit) : RecyclerView.Adapter<MainFavoriteViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MainFavoriteViewHolder {
        return MainFavoriteViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_github_person, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainFavoriteViewHolder, position: Int) {
        holder.bindData(data[position], clickListener)
    }

}

class MainFavoriteViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(data: UsersFavEntity, clickListener: (UsersFavEntity) -> Unit) {
        Glide.with(itemView.context).load(data.avatarUrl).into(itemView.iv_avatar)
        itemView.txt_name.text = data.login

        itemView.setOnClickListener {
            clickListener(data)
        }
    }
}