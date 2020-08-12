package com.example.githubperson.ui.detail_ui.followtab_ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubperson.R
import com.example.githubperson.base.BaseFragment
import com.example.githubperson.data.model.UsersItems
import com.example.githubperson.ui.main_ui.GithubPersonAdapter
import com.example.githubperson.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_follow_tab.*
import kotlinx.android.synthetic.main.unknown_users_layout.*

class FollowTabFragment : BaseFragment<FollowTabViewModel>(), FollowTabContract.View {

    companion object {
        const val ARG_FRAGMENT_POS = "arg_fragment_position"
        const val ARG_FRAGMENT_USERNAME = "arg_fragment_username"
        @JvmStatic
        fun newInstance(pos: Int, username: String) =
            FollowTabFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_FRAGMENT_POS, pos)
                    putString(ARG_FRAGMENT_USERNAME, username)
                }
            }
    }

    private var dataFollowAdapter: GithubPersonAdapter = GithubPersonAdapter(clickListener = { data : UsersItems -> followClicked(data)})

    override fun getLayoutResorceId(): Int = R.layout.fragment_follow_tab

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelfactory)
    }

    override fun getViewModelClass(): Class<FollowTabViewModel> = FollowTabViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
                observeLoading(isLoading)
            })
            isError.observe(viewLifecycleOwner, Observer { error ->
                observeError(error)
            })
            listDataFollow.observe(viewLifecycleOwner, Observer { listData ->
                observeDataFollow(listData)
            })
        }

        initRecyclerView()

        arguments?.let {
            if (it.getInt(ARG_FRAGMENT_POS) == 0) viewModel.getFollowers(it.getString(ARG_FRAGMENT_USERNAME) ?: "")
            else viewModel.getFollowing(it.getString(ARG_FRAGMENT_USERNAME) ?: "")
        }
    }

    private fun initRecyclerView(){
        rvFollowTab.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = dataFollowAdapter
        }

    }

    override fun observeLoading(isLoading: Boolean?) {
        isLoading?.let {
            if (it){
                rvFollowTab.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }else{
                rvFollowTab.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeDataFollow(listData: MutableList<UsersItems>) {
        if (listData.isNotEmpty()) {
            dataFollowAdapter.data = listData
            dataFollowAdapter.notifyDataSetChanged()
        }else{
            wordingUnknownUser.visibility = View.VISIBLE
            wordingUnknown.text = getString(R.string.wording_unknown_follow)
        }
    }

    private fun followClicked(data: UsersItems) {

    }
}