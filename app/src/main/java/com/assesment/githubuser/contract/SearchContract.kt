package com.assesment.githubuser.contract

import com.assesment.domain.model.GithubUser
import com.assesment.shared.viewModel.UiEffect
import com.assesment.shared.viewModel.UiEvent
import com.assesment.shared.viewModel.UiState

class SearchContract {
    sealed class Event : UiEvent {
        data object GetHistory : Event()
        data class SearchUsers(val query: String) : Event()
        data class NextPage(val query: String) : Event()
        data class ClickUser(val user: GithubUser) : Event()
    }

    data class State(
        val usersState: SearchState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
        data class OpenDetail(val user: GithubUser) : Effect()
    }

    sealed class SearchState {
        data object Idle : SearchState()
        data object Loading : SearchState()
        data object LoadingNextPage : SearchState()
        data class Success(val data: List<GithubUser>) : SearchState()
        data class SuccessNextPage(val data: List<GithubUser>, val currentPage: Int) : SearchState()
    }
}