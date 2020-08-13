package com.example.githubperson.ui.detail_ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.githubperson.BuildConfig
import com.example.githubperson.R
import com.example.githubperson.base.BaseActivity
import com.example.githubperson.data.db.entity.UsersFavEntity
import com.example.githubperson.utils.injectViewModel
import com.example.githubperson.utils.loadImageGlide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<DetailViewModel>(), DetailUsersContract.View {

    companion object{
        const val EXTRA_USERNAME = BuildConfig.APPLICATION_ID + ".EXTRA_USERNAME"

        fun intentDetail(
            context: Context,
            username: String
        ): Intent{
            return Intent(context, DetailActivity::class.java)
                .putExtra(EXTRA_USERNAME, username)
        }
    }

    private lateinit var username: String
    private var usersFavEntity: UsersFavEntity? = null
    private var detailFavUsers: UsersFavEntity? = null

    override fun getLayoutResourceId(): Int = R.layout.activity_detail

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun initView() {
        setupToolbar()

        username = intent.getStringExtra(EXTRA_USERNAME) ?: ""

        viewModel.apply {
            isLoading.observe(this@DetailActivity, Observer { isLoading ->
                observeLoading(isLoading)
            })
            isError.observe(this@DetailActivity, Observer { error ->
                observeError(error)
            })
            detailUsers.observe(this@DetailActivity, Observer { detailUsers ->
                observeDetailUsers(detailUsers)
            })
            favoriteUsers.observe(this@DetailActivity, Observer { favoriteUsers ->
                observeCheckFavoriteUsers(favoriteUsers)
            })
            favUsers.observe(this@DetailActivity, Observer { favUsers ->
                observeAddDeleteFavorites(favUsers)
            })
        }

        viewModel.getDetailUsers(username)

        setupFollowTab()

        btnFavorite.setOnClickListener {
            Log.e("GGG", "$usersFavEntity")
            if (usersFavEntity != null) deleteFavorite()
            else addToFavorite()
        }
    }

    private fun setupFollowTab(){
        detailViewPager.adapter =
            DetailSectionPagerAdapter(
                supportFragmentManager,
                this@DetailActivity,
                username
            )
        detailTabLayout.setupWithViewPager(detailViewPager)
    }

    override fun observeLoading(isLoading: Boolean?) {
        isLoading?.let {
            if (it) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        }
    }

    override fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeDetailUsers(detailUsers: UsersFavEntity) {
        detailFavUsers = detailUsers
        detailUsers.let {
            loadImageGlide(this, it.avatarUrl, iv_detail_avatar)
            txtDetailUsername.text = it.login
            txtFollowers.text = it.followers.toString()
            txtFollowing.text = it.following.toString()
            txtRepository.text = it.publicRepos.toString()
            txtDetailName.text = it.name
            txtDetailLocation.text = it.location ?: getString(R.string.location_unknown)

            viewModel.checkFavoriteUser(it.id!!, this)
        }
    }

    override fun observeCheckFavoriteUsers(favUsers: UsersFavEntity?) {
        usersFavEntity = favUsers
        checkIsFavorite()
    }

    override fun observeAddDeleteFavorites(favUsers: Long) {
        if (favUsers == 1L){
            usersFavEntity = detailFavUsers
            Toast.makeText(this, getString(R.string.toast_add_fav), Toast.LENGTH_SHORT).show()
        }else{
            usersFavEntity = null
            Toast.makeText(this, getString(R.string.toast_del_fav), Toast.LENGTH_SHORT).show()
        }
        checkIsFavorite()
    }

    private fun checkIsFavorite(){
        val drawable =
        if (usersFavEntity != null) resources.getDrawable(R.drawable.ic_favorite_filled, null)
        else resources.getDrawable(R.drawable.ic_favorite_add, null)

        btnFavorite.setImageDrawable(drawable)
    }

    private fun addToFavorite(){
        detailFavUsers?.let {
            viewModel.addFavoriteUser(it, this)
        }
    }

    private fun deleteFavorite(){
        usersFavEntity?.let {
            viewModel.deleteFavoriteUser(it, this)
        }
    }

    private fun setupToolbar(){
        supportActionBar?.title = getString(R.string.detail_person)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}