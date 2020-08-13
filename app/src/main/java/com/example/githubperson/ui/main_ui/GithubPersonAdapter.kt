package com.example.githubperson.ui.main_ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubperson.R
import com.example.githubperson.data.model.UsersItems
import com.example.githubperson.utils.loadImageGlide
import kotlinx.android.synthetic.main.item_list_github_person.view.*

class GithubPersonAdapter(var data: List<UsersItems> = listOf(), val clickListener: (UsersItems) -> Unit) : RecyclerView.Adapter<GithubPersonViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): GithubPersonViewHolder {
        return GithubPersonViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_github_person, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: GithubPersonViewHolder, position: Int) {
        holder.bindData(data[position], clickListener)
    }

}

class GithubPersonViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(data: UsersItems, clickListener: (UsersItems) -> Unit) {
        loadImageGlide(itemView.context, data.avatarUrl, itemView.iv_avatar)
        itemView.txt_name.text = data.login

        itemView.setOnClickListener {
            clickListener(data)
        }
    }
}