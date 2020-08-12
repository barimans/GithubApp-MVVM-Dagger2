package com.example.githubperson.ui.main_ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubperson.R
import com.example.githubperson.base.BaseActivity
import com.example.githubperson.data.model.UsersItems
import com.example.githubperson.ui.detail_ui.DetailActivity
import com.example.githubperson.ui.favorite_ui.FavoriteActivity
import com.example.githubperson.ui.settings_ui.SettingsActivity
import com.example.githubperson.utils.injectViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(), MainUsersContract.View {

  private var dataGithubAdapter: GithubPersonAdapter = GithubPersonAdapter(clickListener = { data : UsersItems -> userClicked(data)})

  private var searchView: SearchView? = null

  override fun injectViewModel() {
    mViewModel = injectViewModel(viewModelFactory)
  }

  override fun initView() {
    viewModel.apply {
      isLoading.observe(this@MainActivity, Observer { isLoading ->
        observeLoading(isLoading)
      })
      isError.observe(this@MainActivity, Observer { error ->
        observeError(error)
      })
      users.observe(this@MainActivity, Observer { users ->
        observeSearchUsers(users)
      })
    }

    initRecyclerView()

    btnFavoriteList.setOnClickListener {
      startActivity(FavoriteActivity.intentFavorite(this))
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.action_menu, menu)

    val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
    searchView = menu.findItem(R.id.search).actionView as SearchView

    searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
    searchView?.queryHint = resources.getString(R.string.search_hint)
    searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

      override fun onQueryTextSubmit(query: String): Boolean {
        Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
        return false
      }

      override fun onQueryTextChange(newText: String): Boolean {
        if (newText.isNotEmpty()){
          viewModel.searchUsers(newText)
        }
        return true
      }
    })
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.settings){
      startActivity(Intent(this, SettingsActivity::class.java))
    }
    return super.onOptionsItemSelected(item)
  }

  override fun getLayoutResourceId(): Int = R.layout.activity_main

  private fun initRecyclerView(){
    rvGithubUsers.apply {
      layoutManager = LinearLayoutManager(this@MainActivity)
      adapter = dataGithubAdapter
    }

  }

  override fun observeLoading(isLoading: Boolean?) {
    isLoading?.let {
      if (it) {
        wordingUnknownUser.visibility = View.GONE
        rvGithubUsers.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
      }
      else{
        wordingUnknownUser.visibility = View.GONE
        rvGithubUsers.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
      }
    }
  }

  override fun observeError(error: Throwable?) {
    error?.let { Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
  }

  override fun observeSearchUsers(users: List<UsersItems>) {
    dataGithubAdapter.data = users
    dataGithubAdapter.notifyDataSetChanged()
  }

  private fun userClicked(data: UsersItems) {
    startActivity(DetailActivity.intentDetail(this, data.login!!))
  }
}