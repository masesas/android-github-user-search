package com.assesment.githubuser.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.assesment.domain.model.GithubUser
import com.assesment.githubuser.constant.WorkerConstant
import com.assesment.githubuser.contract.SearchContract
import com.assesment.githubuser.databinding.FragmentSearchBinding
import com.assesment.githubuser.worker.CachedSearchWorker
import com.assesment.shared.extension.gone
import com.assesment.shared.extension.hideKeyboard
import com.assesment.shared.extension.showToast
import com.assesment.shared.extension.visible
import com.assesment.shared.ui.BaseFragment
import com.assesment.shared.ui.GridSpacingItemDecoration
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private var isHistory = false

    private val viewModel: SearchViewModel by viewModel()

    private val searchRvAdapter: SearchResultAdapter by lazy {
        SearchResultAdapter(onClick = { user ->
            viewModel.setEvent(
                SearchContract.Event.ClickUser(
                    user!!
                )
            )
        })
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        initRv()
        initSearch()
        initObservers()

        if (viewModel.currentState.usersState is SearchContract.SearchState.Idle) {
            isHistory = true
            viewModel.setEvent(SearchContract.Event.GetHistory)
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.usersState) {
                        is SearchContract.SearchState.Idle -> {
                            binding.tvEmpty.visible()
                            binding.pbLoading.gone()
                            binding.rvUsers.gone()
                            binding.pbLoadingMore.gone()
                        }

                        is SearchContract.SearchState.Loading -> {
                            binding.tvEmpty.gone()
                            binding.pbLoading.visible()
                            binding.rvUsers.gone()
                            binding.pbLoadingMore.gone()
                        }

                        is SearchContract.SearchState.Success -> {
                            runCacheWorker(state.data.toMutableList())

                            binding.tvEmpty.gone()
                            binding.pbLoading.gone()
                            binding.rvUsers.visible()
                            binding.pbLoadingMore.gone()

                            searchRvAdapter.submitList(state.data.toMutableList())
                        }

                        is SearchContract.SearchState.LoadingNextPage -> {
                            binding.pbLoadingMore.visible()
                        }

                        is SearchContract.SearchState.SuccessNextPage -> {
                            runCacheWorker(state.data.toMutableList())

                            binding.pbLoadingMore.gone()

                            val list = searchRvAdapter.currentList.toMutableList()
                            list.addAll(state.data)
                            searchRvAdapter.submitList(list)
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEffect.collect {
                    when (it) {
                        is SearchContract.Effect.ShowError -> {
                            binding.tvEmpty.visible()
                            binding.pbLoading.gone()
                            activity?.showToast(it.message)
                        }

                        is SearchContract.Effect.OpenDetail -> {
                            val direction =
                                SearchFragmentDirections.actionFragmentSearchToFragmentSearchDetail()
                                    .apply {
                                        username = it.user.username
                                    }

                            findNavController().navigate(direction)
                        }
                    }
                }
            }
        }
    }

    private fun initRv() {
        binding.rvUsers.apply {
            adapter = searchRvAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            addItemDecoration(GridSpacingItemDecoration(3, 50, true))

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val lastPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastPosition == searchRvAdapter.itemCount.minus(1)) {
                        viewModel.setEvent(
                            SearchContract.Event.NextPage(
                                query = binding.searchBox.etSearch.text.toString(),
                            )
                        )
                    }
                }
            })
        }
    }

    private fun initSearch() {
        binding.searchBox.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                isHistory = false
                requireContext().hideKeyboard(binding.root)
                viewModel.setEvent(SearchContract.Event.SearchUsers(query = v.text.toString()))
                true
            } else {
                false
            }
        }
    }

    private fun runCacheWorker(list: List<GithubUser>) {
        if (isHistory) return

        val dataList = workDataOf(
            WorkerConstant.KEY_CACHED_SEARCH_USER_WORKER to Gson().toJson(list),
            WorkerConstant.KEY_CACHED_SEARCH_QUERY_WORKER to binding.searchBox.etSearch.text.toString()
        )

        val workRequest = OneTimeWorkRequestBuilder<CachedSearchWorker>()
            .setInputData(dataList)
            .setConstraints(Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false)
                .build())
            .build()

        WorkManager
            .getInstance(requireActivity().applicationContext)
            .enqueue(workRequest)

    }
}