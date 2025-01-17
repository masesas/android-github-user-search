package com.assesment.githubuser.ui.search

import androidx.lifecycle.viewModelScope
import com.assesment.domain.model.GithubUser
import com.assesment.domain.repository.GtihubUserRepository
import com.assesment.domain.usecase.GetHistorySearchUserParams
import com.assesment.domain.usecase.GetHistorySearchUserUseCase
import com.assesment.domain.usecase.GetSearchGithubUser
import com.assesment.domain.usecase.GetSearchGithubUserParams
import com.assesment.githubuser.contract.SearchContract
import com.assesment.shared.utils.Resource
import com.assesment.shared.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val historyGithubUserRepository: GtihubUserRepository,
    private val getHistoryGithubUser: GetHistorySearchUserUseCase,
    private val getSearchGithubUser: GetSearchGithubUser
) : BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {

    private val _page: MutableStateFlow<Int> = MutableStateFlow(1)

    val page: StateFlow<Int> get() = _page.asStateFlow()

    override fun createInitialState(): SearchContract.State = SearchContract.State(
        usersState = SearchContract.SearchState.Idle
    )

    override fun handleEvent(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.SearchUsers -> {
                getApi(event.query)
            }

            is SearchContract.Event.NextPage -> {
                getApi(query = event.query, page = page.value + 1)
            }

            SearchContract.Event.GetHistory -> {
                getHistory(query = "", page = page.value + 1)
            }

            is SearchContract.Event.ClickUser -> {
                setEffect { SearchContract.Effect.OpenDetail(event.user) }
            }
        }
    }

    private fun getApi(query: String, page: Int = 1) {
        _page.value = page

        viewModelScope.launch {
            getSearchGithubUser.run(GetSearchGithubUserParams(query = query, page = page))
                .onStart { emit(Resource.Loading) }
                .collect {
                    emitState(it, page)
                }
        }
    }

    private fun getHistory(query: String, page: Int = 1) {
        viewModelScope.launch {
            getHistoryGithubUser.run(GetHistorySearchUserParams(query = query, page = 1))
                .onStart { emit(Resource.Loading) }
                .collect {
                    emitState(it, page)
                }
        }
    }

    private fun emitState(result: Resource<List<GithubUser>>, page: Int = 1) {
        _page.value = page

        when (result) {
            is Resource.Empty -> setState { copy(usersState = SearchContract.SearchState.Idle) }
            is Resource.Error -> setEffect { SearchContract.Effect.ShowError(message = result.exception.message) }
            is Resource.Loading -> setState { copy(usersState = if (page == 1) SearchContract.SearchState.Loading else SearchContract.SearchState.LoadingNextPage) }
            is Resource.Success -> setState {
                if (page == 1) {
                    copy(
                        usersState = SearchContract.SearchState.Success(
                            result.data
                        )
                    )
                } else {
                    copy(
                        usersState = SearchContract.SearchState.SuccessNextPage(
                            result.data,
                            page
                        )
                    )
                }
            }
        }
    }
}