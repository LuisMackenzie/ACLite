package com.mackenzie.aclite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mackenzie.aclite.R
import com.mackenzie.aclite.data.LoginRepository
import com.mackenzie.aclite.domain.TryLoginUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val tryLoginUseCase: TryLoginUseCase = TryLoginUseCase()
): ViewModel() {

    private val _state = MutableLiveData<UiState>(UiState())
    public val state:LiveData<UiState> get() = _state

    public fun onTryLogin(user:String, pass: String) {
        viewModelScope.launch {
            _state.value = UiState(loggingIn = true)
            val result = tryLoginUseCase(user, pass)
            _state.value = UiState(
                userError = if (result.userError) R.string.username_error else null,
                passError = if (result.passError) R.string.password_error else null,
                loggedIn = result.success

            )

        }
    }

    fun onNavigateToNextScreen() {
        _state.value = requireNotNull(_state.value).copy(loggedIn = false)
    }

    data class UiState(
        val loggingIn:Boolean = false,
        val loggedIn:Boolean = false,
        val userError:Int? = null,
        val passError:Int? = null
        )


    sealed class UiStateOld {
        object Loading: UiStateOld()
        data class Error(val userError: String, val passError:String): UiStateOld()
        object LoggedIn: UiStateOld()
    }

}