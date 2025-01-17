package com.assesment.githubuser.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.assesment.githubuser.contract.SearchDetailContract
import com.assesment.githubuser.databinding.FragmentSearchDetailBinding
import com.assesment.githubuser.utils.loadingImgPlaceHolder
import com.assesment.shared.extension.showToast
import com.assesment.shared.ui.BaseFragment
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchDetailFragment : BaseFragment<FragmentSearchDetailBinding>() {

    private val args: SearchDetailFragmentArgs by navArgs()

    private val viewModel: SearchDetailViewModel by viewModel()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchDetailBinding
        get() = FragmentSearchDetailBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        initObservers()

        if (args.username.isNotEmpty()) {
            viewModel.setEvent(SearchDetailContract.Event.GetUserDetails(args.username))
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.userDetailState) {
                        is SearchDetailContract.SearchDetailState.Idle -> {

                        }

                        is SearchDetailContract.SearchDetailState.Loading -> {}
                        is SearchDetailContract.SearchDetailState.Success -> {
                            binding.tvName.text = state.data.name
                            binding.tvUsername.text = state.data.username
                            binding.tvBio.text = state.data.bio
                            binding.tvJoinDate.text = state.data.createdAt
                            binding.tvTotalRepo.text = "${state.data.publicRepos}"
                            binding.tvTotalFollower.text = "${state.data.followers}"
                            binding.tvTotalFollowing.text = "${state.data.following}"

                            Glide.with(requireContext())
                                .load(state.data.avatarUrl)
                                .placeholder(loadingImgPlaceHolder(requireContext()))
                                .into(binding.imgAvatar)
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEffect.collect {
                    when (it) {
                        is SearchDetailContract.Effect.ShowError -> {
                            activity?.showToast(it.message)
                        }
                    }
                }
            }
        }
    }
}