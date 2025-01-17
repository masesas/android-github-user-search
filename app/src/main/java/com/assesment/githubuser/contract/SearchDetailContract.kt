package com.assesment.githubuser.contract

import com.assesment.domain.model.GithubUserDetail
import com.assesment.shared.viewModel.UiEffect
import com.assesment.shared.viewModel.UiEvent
import com.assesment.shared.viewModel.UiState

class SearchDetailContract {
    sealed class Event : UiEvent {
        data class GetUserDetails(val username: String) : Event()
    }

    data class State(
        val userDetailState: SearchDetailState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }

    sealed class SearchDetailState {
        data object Idle : SearchDetailState()
        data object Loading : SearchDetailState()
        data class Success(val data: GithubUserDetail) : SearchDetailState()
    }
}