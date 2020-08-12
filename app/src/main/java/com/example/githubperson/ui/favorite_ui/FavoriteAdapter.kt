package com.example.githubperson.ui.favorite_ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubperson.R
import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.data.model.UsersItems
import com.example.githubperson.utils.loadImageGlide
import kotlinx.android.synthetic.main.item_list_github_person.view.*

class FavoriteAdapter(var data: List<UsersFavEntity> = listOf(), val clickListener: (UsersFavEntity) -> Unit) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_github_person, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindData(data[position], clickListener)
    }

}

class FavoriteViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(data: UsersFavEntity, clickListener: (UsersFavEntity) -> Unit) {
        loadImageGlide(itemView.context, data.avatarUrl, itemView.iv_avatar)
        itemView.txt_name.text = data.login

        itemView.setOnClickListener {
            clickListener(data)
        }
    }
}