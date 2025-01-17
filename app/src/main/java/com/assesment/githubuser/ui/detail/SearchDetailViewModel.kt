package com.assesment.githubuser.ui.detail

import androidx.lifecycle.viewModelScope
import com.assesment.domain.usecase.GetGithubUserDetailUseCase
import com.assesment.domain.usecase.GetGithubUserDetailParams
import com.assesment.githubuser.contract.SearchDetailContract
import com.assesment.shared.utils.Resource
import com.assesment.shared.viewModel.BaseViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchDetailViewModel(
    private val getGithubUserDetailUseCase: GetGithubUserDetailUseCase
) : BaseViewModel<SearchDetailContract.Event, SearchDetailContract.State, SearchDetailContract.Effect>() {
    override fun createInitialState(): SearchDetailContract.State = SearchDetailContract.State(
        userDetailState = SearchDetailContract.SearchDetailState.Idle
    )

    override fun handleEvent(event: SearchDetailContract.Event) {
        when (event) {
            is SearchDetailContract.Event.GetUserDetails -> {
                getUserDetail(event.username)
            }
        }
    }

    private fun getUserDetail(username: String) {
        viewModelScope.launch {
            getGithubUserDetailUseCase
                .execute(GetGithubUserDetailParams(username = username))
                .onStart { }
                .collect {
                    when (it) {
                        is Resource.Loading -> setState { copy(userDetailState = SearchDetailContract.SearchDetailState.Loading) }
                        is Resource.Empty -> setState { copy(userDetailState = SearchDetailContract.SearchDetailState.Idle) }
                        is Resource.Error -> setEffect {
                            SearchDetailContract.Effect.ShowError(
                                message = it.exception.message
                            )
                        }

                        is Resource.Success -> setState {
                            copy(
                                userDetailState = SearchDetailContract.SearchDetailState.Success(
                                    it.data
                                )
                            )
                        }
                    }
                }

        }
    }
}