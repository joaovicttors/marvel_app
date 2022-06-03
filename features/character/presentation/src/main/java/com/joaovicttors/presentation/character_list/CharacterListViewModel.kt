package com.joaovicttors.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaovicttors.bases.Response
import com.joaovicttors.bases.domain.BaseUseCase
import com.joaovicttors.domain.entities.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val getCharacterListUseCase: BaseUseCase<Nothing, List<Character>>,
) : ViewModel() {

    val viewState: StateFlow<CharacterListViewState> get() = _viewState
    private val _viewState = MutableStateFlow(CharacterListViewState())

    fun getCharacterList() {
        viewModelScope.launch(dispatcher) { ->
            try {
                _viewState.update { it.copy(isLoading = true) }

                when (val response = getCharacterListUseCase()) {
                    is Response.Error -> _viewState.update { it.copy(errorMessage = response.message) }
                    is Response.Success -> _viewState.update { it.copy(data = response.data) }
                }

            } catch (error: Exception) {
                _viewState.update { it.copy(errorMessage = error.message) }
            } finally {
                _viewState.update { it.copy(isLoading = false) }
            }
        }
    }
}