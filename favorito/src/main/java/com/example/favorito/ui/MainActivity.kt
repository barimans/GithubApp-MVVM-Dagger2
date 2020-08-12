package com.example.favorito.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorito.R
import com.example.favorito.data.UsersFavEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainPresenter
    private var dataFavoriteAdapter: MainAdapter = MainAdapter(clickListener = { data : UsersFavEntity -> mainFavoriteClicked(data)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        presenter.getListFavoriteUsers(this)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        rvUsersFavorite.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = dataFavoriteAdapter
        }

    }

    override fun observeFavoriteUsers(dataFavorite: List<UsersFavEntity>) {
        dataFavoriteAdapter.data = dataFavorite
        dataFavoriteAdapter.notifyDataSetChanged()
    }

    override fun observeError(error: Throwable) {
        error.let { Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeLoading(isLoading: Boolean) {
        isLoading.let {
            if (it) {
                wordingUnknownUser.visibility = View.GONE
                rvUsersFavorite.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            else{
                wordingUnknownUser.visibility = View.GONE
                rvUsersFavorite.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun mainFavoriteClicked(data: UsersFavEntity) {}
}
