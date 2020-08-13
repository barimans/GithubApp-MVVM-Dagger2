package com.example.githubperson.ui.favorite_ui

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubperson.R
import com.example.githubperson.base.BaseActivity
import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.ui.detail_ui.DetailActivity
import com.example.githubperson.utils.injectViewModel
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.unknown_users_layout.*

class FavoriteActivity : BaseActivity<FavoriteViewModel>(), FavoriteContract.View {

    companion object{
        fun intentFavorite(context: Context): Intent {
            return Intent(context, FavoriteActivity::class.java)
        }
    }

    private var dataFavoriteAdapter: FavoriteAdapter = FavoriteAdapter(clickListener = { data : UsersFavEntity -> favoriteClicked(data)})

    override fun getLayoutResourceId(): Int = R.layout.activity_favorite

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun initView() {
        setupToolbar()

        viewModel.apply {
            isLoading.observe(this@FavoriteActivity, Observer { isLoading ->
                observeLoading(isLoading)
            })
            isError.observe(this@FavoriteActivity, Observer { error ->
                observeError(error)
            })
            favoriteUsers.observe(this@FavoriteActivity, Observer { favUsers ->
                observeFavoriteUsers(favUsers)
            })
        }

        viewModel.getListFavoriteUsers(this)

        initRecyclerView()

    }

    private fun initRecyclerView(){
        rvUsersFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = dataFavoriteAdapter
        }

    }

    override fun observeLoading(isLoading: Boolean?) {
        isLoading?.let {
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

    override fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeFavoriteUsers(favoriteUsers: MutableList<UsersFavEntity>) {
        if (favoriteUsers.isNotEmpty()){
            rvUsersFavorite.visibility = View.VISIBLE
            wordingUnknownUser.visibility = View.GONE
        }else{
            rvUsersFavorite.visibility = View.GONE
            wordingUnknownUser.visibility = View.VISIBLE
            wordingUnknown.text = getString(R.string.wording_unknown_follow)
        }

        dataFavoriteAdapter.data = favoriteUsers
        dataFavoriteAdapter.notifyDataSetChanged()

    }

    private fun favoriteClicked(data: UsersFavEntity) {
        startActivity(DetailActivity.intentDetail(this, data.login!!))
    }

    private fun setupToolbar(){
        supportActionBar?.title = getString(R.string.users_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel != null){
            viewModel.getListFavoriteUsers(this)
        }
    }
}